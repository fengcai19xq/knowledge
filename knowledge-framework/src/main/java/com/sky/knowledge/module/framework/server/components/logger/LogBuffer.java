package com.sky.knowledge.module.framework.server.components.logger;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.sky.knowledge.module.framework.server.components.logger.track.SendTask;
import com.sky.knowledge.module.framework.shared.exception.logger.BufferedInitException;
import com.sky.knowledge.module.framework.shared.exception.logger.BufferedStateException;


public class LogBuffer implements InitializingBean,DisposableBean {
	
	/**
	 * 
	 */
	private int queueCapacity = 5000;
	
	/**
	 *阻塞队列元素的长度
	 */
	private int size = 20;
	
	/**
	 * 初始化线程的数量
	 */
	private int threads;
	
	/**
	 * 是否启动日志缓存
	 */
	private boolean enable = true;
	
	/**
	 * 日志缓冲开关标志
	 */
	private AtomicBoolean shutdown = new AtomicBoolean(false);

	/**
	 * 日志集合队列
	 */
	@SuppressWarnings("rawtypes")
	BlockingDeque<ArrayList> queuePool;
	
	/**
	 * 日志发送线程池
	 */
	ExecutorService threadPool;
	
	/**
	 * 日志发送器
	 */
	private LogSender sender;

	/**
	 * 
	 * getQueueCapacity
	 * @return
	 * @return int
	 * @since JDK1.6
	 */
	public int getQueueCapacity() {
		return queueCapacity;
	}

	/**
	 * 
	 * setQueueCapacity
	 * @param queueCapacity
	 * @return void
	 * @since JDK1.6
	 */
	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	/**
	 * 
	 * setSize
	 * @param size
	 * @return void
	 * @since JDK1.6
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 
	 * setThreads
	 * @param threads
	 * @return void
	 * @since JDK1.6
	 */
	public void setThreads(int threads) {
		this.threads = threads;
	}

	/**
	 * 
	 * setSender
	 * @param sender
	 * @return void
	 * @since JDK1.6
	 */
	public void setSender(LogSender sender) {
		this.sender = sender;
	}
	
	/**
	 * 
	 * isEnable
	 * @return
	 * @return boolean
	 * @since JDK1.6
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * 
	 * setEnable
	 * @param enable
	 * @return void
	 * @since JDK1.6
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	/**
	 *  如果队列的元素个数和队列的元素的容量设置正确，进行下面操作:
	 * 1、 初始化日志缓冲
	 * 2、初始化阻塞队列
	 *  3、初始化队列中的缓冲区
	 *  4、初始化线程池
	 *  init
	 * @return void
	 * @since JDK1.6
	 */
	@SuppressWarnings("rawtypes")
	private void init() {
		check();
		queuePool = new LinkedBlockingDeque<ArrayList>(size);
		initQueues();
		threadPool = Executors.newFixedThreadPool(threads);
	}

	/**
	 * 初始化队列中的缓冲区
	 * initQueues
	 * @return void
	 * @since JDK1.6
	 */
	@SuppressWarnings("rawtypes")
	private void initQueues() {
		for (int i = 0; i < size; i++) {
			ArrayList list = new ArrayList(queueCapacity);
			try {
				queuePool.put(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 检查队列的初始化元素个数、队列元素的初始化容量是否正确，
	 * 若不正确则抛出初始化异常
	 * check
	 * @return void
	 * @since JDK1.6
	 */
	private void check() {
		if (queueCapacity <= 0)
			throw new BufferedInitException("queuecapacity can not <= 0");
		if (size < 1)
			throw new BufferedInitException("size can not < 1");
	}
	
	/**
	 * 记录日志
	 * 将需要写入日志的对象放入到日志缓冲中，每次取出日志缓冲阻塞队列中的首个日志缓冲，
	 * 	并向其添加需要记录的日志信息，若日志缓冲已经达到容量限制，则通过异步jms发送该日志缓冲
	 * 并在队列的末尾添加一个新的日志缓冲
	 * 以下3种情况将不记录日志：1、禁止启用缓存；2、日志缓冲已经关闭；3、需要写入的对象为null
	 * write
	 * @param obj
	 * @return void
	 * @since JDK1.6
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void write(Object obj) {
		//如果日志缓冲禁止,则什么都不做
		//默认开启
		if (!enable)
			return;
		//判断日志缓冲的状态
		//如果已关闭则抛异常
		if (shutdown.get())
			throw new BufferedStateException("buffer is shutdown");
		if (obj == null)
			return;
		ArrayList list;
		try {
			//从队头取出缓冲块
			list = queuePool.takeFirst();
			//将日志放入缓冲块
			list.add(obj);
			//如果缓冲块已满,则新建一个发送任务交给线程池发送
			//并且新建一个空的缓冲块放入队尾
			if (list.size() == queueCapacity) {
				queuePool.putLast(new ArrayList(queueCapacity));
				threadPool.submit(new SendTask(list, sender));
			}
			//否则将缓冲块放回队头
			else
				queuePool.putFirst(list);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 停止队列
	 * 首先修改缓冲的关闭状态为“关闭”
	 * 然后将队列中所有的数据发送
	 * shutdown
	 * @return void
	 * @since JDK1.6
	 */
	public void shutdown() {
		shutdown.set(true);
		@SuppressWarnings("rawtypes")
		ArrayList list = null;
		/**
		 * 将缓存中的所有日志发送
		 */
		while ((list = queuePool.poll()) != null) {
			if (list.size() == 0)
				continue;
			threadPool.submit(new SendTask(list, sender));
		}
		/**
		 * 关闭线程池
		 */
		threadPool.shutdown();
		//确保线程池已经关闭
		/**
		 * 循环：若线程池未完全关闭，则等待1000ms，使得线程池完全关闭
		 */
		while (!threadPool.isTerminated())
			try {
				synchronized (this) {
					this.wait(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 属性注入完毕后，调用初始化方法
	 * 初始化阻塞队列、线程池以及阻塞队列中的缓冲区
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 * afterPropertiesSet
	 * @throws Exception
	 * @since JDK1.6
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	/**
	 * 关闭时，调用shutdown（）方法，停止队列
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 * destroy
	 * @throws Exception
	 * @since JDK1.6
	 */
	@Override
	public void destroy() throws Exception {
		shutdown();
	}
}
