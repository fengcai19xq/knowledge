import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;

/**
 * @description
 * @create xq
 * @date 2014-11-15  
 */
public class MongoDB4CRUDTest {

	 private Mongo mg = null;
	 private DB db;
	 private DBCollection users;
	
	    @Before
	    public void init() {
	        try {
	            mg = new Mongo();
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (MongoException e) {
	            e.printStackTrace();
	        }
	        //获取temp DB；如果默认没有创建，mongodb会自动创建
	        db = mg.getDB("temp");
	        //获取users DBCollection；如果默认没有创建，mongodb会自动创建
	        users = db.getCollection("users");
	    }
	    @After
	    public void destory() {
	        if (mg != null)
	            mg.close();
	        mg = null;
	        db = null;
	        users = null;
	        System.gc();
	    }
	    
	    public void print(Object o) {
	        System.out.println(o);
	    }

	    private void queryAll() {
	        print("查询users的所有数据：");
	        //db游标
	        DBCursor cur = users.find();
	        while (cur.hasNext()) {
	            print(cur.next());
	        }
	    }
	    
	    @Test
	    public void add() {
	        //先查询所有数据
	        queryAll();
	        print("count: " + users.count());
	        
	        DBObject user = new BasicDBObject();
	        user.put("name", "hoojo");
	        user.put("age", 24);
	        //users.save(user)保存，getN()获取影响行数
	        //print(users.save(user).getN());
	        
	        //扩展字段，随意添加字段，不影响现有数据
	        user.put("sex", "男");
	        print(users.save(user).getN());
	        
	        
	        //添加多条数据，传递Array对象
//	        print(users.insert(user, new BasicDBObject("name", "tom")).getN());
	        
	        //添加List集合
//	        List<DBObject> list = new ArrayList<DBObject>();
//	        list.add(user);
//	        DBObject user2 = new BasicDBObject("name", "lucy");
//	        user.put("age", 22);
//	        list.add(user2);
//	        //添加List集合
//	        print(users.insert(list).getN());
	        
	        //查询下数据，看看是否添加成功
	        print("count: " + users.count());
	        queryAll();
	    }
	    
//	    @Test
	    public void remove() {
	        queryAll();
	        print("删除id = 5466bd9982a55fcd4bf3c3ea：" + users.remove(new BasicDBObject("_id", new ObjectId("5466bd9982a55fcd4bf3c3ea"))).getN());
	        print("remove age >= 24: " + users.remove(new BasicDBObject("age", new BasicDBObject("$gte", 24))).getN());
	    }
	    
//	    @Test
	    public void modify() {
//	        print("修改：" + users.update(new BasicDBObject("_id", new ObjectId("5466be6b82a55a7b48207447")), new BasicDBObject("age", 99)).getN());//相当于根据id 覆盖以前的记录
//	        print("修改：" + users.update(
//	        		new BasicDBObject("age", new BasicDBObject("$gte", 99)), 
//	                new BasicDBObject("age", 1212),
//	                true,//如果数据库不存在，是否添加
//	                false//false只修改第一条
//	                ).getN());
//	        print("修改：" + users.update(
//	                new BasicDBObject("name", "lucy"), 
//	                new BasicDBObject("name", "dingding"),
//	                true,//如果数据库不存在，是否添加
//	                true//true如果有多条就不修改
//	                ).getN());
//	        
	        //当数据库不存在就不修改、不添加数据，当多条数据就不修改
//	        print("修改多条：" + users.updateMulti(new BasicDBObject("age", 121), new BasicDBObject("age", "199")).getN());
	        
	        //局部更新
	        print("修改：" + users.update(new BasicDBObject("_id", new ObjectId("5466beb982a59cf1d59dcb1c")), new BasicDBObject("$set",new BasicDBObject("age", 999))).getN());//相当于根据id 覆盖以前的记录

	        queryAll();
	    }
	    
//	    @Test
	    public void query() {
	        //查询所有
	        //queryAll();
	        
	        //查询id = 4de73f7acd812d61b4626a77
//	        print("find id = 5466beb982a59cf1d59dcb1c: " + users.find(new BasicDBObject("_id", new ObjectId("5466beb982a59cf1d59dcb1c"))).toArray());
	        
//	        //查询age = 22
//	        print("find age = 22: " + users.find(new BasicDBObject("age", 22)).toArray());
//	        
//	        //查询age >= 24
	        print("find age >= 24: " + users.find(new BasicDBObject("age", new BasicDBObject("$gte", 24))).toArray());
	        print("find age <= 24: " + users.find(new BasicDBObject("age", new BasicDBObject("$lte", 24))).toArray());
	        
	        print("查询age!=25：" + users.find(new BasicDBObject("age", new BasicDBObject("$ne", 25))).toArray());
	        print("查询age in 25/26/27：" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.IN, new int[] { 25, 26, 27 }))).toArray());
	        print("查询age not in 25/26/27：" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.NIN, new int[] { 25, 26, 27 }))).toArray());
	        print("查询age exists 排序：" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.EXISTS, true))).toArray());
	        
	        print("只查询age属性：" + users.find(null, new BasicDBObject("age", true)).toArray());
	        print("只查属性：" + users.find(null, new BasicDBObject("age", true), 0, 2).toArray());
	        print("只查属性：" + users.find(null, new BasicDBObject("age", true), 0, 2, Bytes.QUERYOPTION_NOTIMEOUT).toArray());
	        
	        //只查询一条数据，多条去第一条
	        print("findOne: " + users.findOne());
	        print("findOne: " + users.findOne(new BasicDBObject("age", 22)));
	        print("findOne: " + users.findOne(new BasicDBObject("age", 22), new BasicDBObject("name", true)));
	        
//	        //查询修改、删除
	        print("findAndRemove 查询age=1212的数据，并且删除: " + users.findAndRemove(new BasicDBObject("age", 1212)));
//	        
//	        //查询age=26的数据，并且修改name的值为Abc
	        print("findAndModify: " + users.findAndModify(new BasicDBObject("age", 26), new BasicDBObject("name", "Abc")));
	        print("findAndModify: " + users.findAndModify(
	            new BasicDBObject("age", 28), //查询age=28的数据
	            new BasicDBObject("name", true), //查询name属性
	            new BasicDBObject("age", true), //按照age排序
	            false, //是否删除，true表示删除
	            new BasicDBObject("name", "Abc"), //修改的值，将name修改成Abc
	            true, 
	            true));
	        
	        queryAll();
	    }
	    
}
