
Ext.define("Ext.ux.grid.DPrinter",{

	requires: 'Ext.XTemplate',

	statics: {
		print: function(grid) {
			var me=this;
			me.initColumns(grid.columns);
			var columns = this.columns;
			me.headings=me.initHeader(grid.columns);
			
			var data = [];
			grid.store.data.each(function(item) {
				var convertedData = [];
				for (var key in item.data) {
					var value = item.data[key];

					Ext.each(columns, function(column) {
						if (column.dataIndex == key) {
							convertedData[key] = column.renderer ? column.renderer(value) : value;
						}
					}, this);
				}

				data.push(convertedData);
			});
			var body     = Ext.create('Ext.XTemplate', this.bodyTpl).apply(columns);
			
			var htmlMarkup = [
				'<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">',
				'<html>',
				  '<head>',
				    '<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />',
				    '<link href="' + this.stylesheetPath + '" rel="stylesheet" type="text/css" media="screen,print" />',
				    '<title></title>',
				  '</head>',
				  '<body>',
				    '<table>'+this.headings+
				      '<tpl for=".">',
				        body,
				      '</tpl>',
				    '</table>',
				  '</body>',
				'</html>'           
			];

			var html = Ext.create('Ext.XTemplate', htmlMarkup).apply(data); 
			
			var win = window.open('', 'printgrid');

			win.document.write(html);

			if (this.printAutomatically){
				win.print();
				win.close();
			}
		},
		/**遍历需要显示的列,保存到columns数组中
		*/
		initColumns:function(columns){
			var me=this;
			Ext.each(columns,function(column){
				if(column.dataIndex!=null&&column.dataIndex!=""){
					me.columns.push(column);
				}
				else{
					me.initColumns(column.items.items);
				}
			});
		},
		/**生成表头html
		*/
		initHeaderCtl:function(columns,index,maxrow){
			var me=this;
			var html='<tr align=center>';
			var colspan=0;
			Ext.each(columns,function(column){
				if(column.dataIndex!=null&&column.dataIndex!=""){
					if(maxrow-index>1)html+='<th rowspan='+eval(maxrow-index)+'>'+column.text+'</th>';
					else html+='<th>'+column.text+'</th>';
					colspan++;
				}
				else{
					var temp=me.initHeaderCtl(column.items.items,index+1,maxrow);
					html+='<th colspan='+temp+'>'+column.text+'</th>';
					colspan+=temp;
				}
				
			});
			html+='</tr>';
			var temp=this.headerCtl[index];
			if(temp!=null && temp!=''){
				html=temp+html;
			}
			this.headerCtl[index]=html;
			return colspan;
		},
		
		initHeader:function(columns){
			var me=this;
			me.getMaxRows(columns,1);
			me.initHeaderCtl(columns,0,me.maxrow);
			var headerCtls=me.headerCtl;
			var headings='';
			Ext.each(headerCtls,function(headerCtl){
				headings+=headerCtl;
			});
			return headings;
		},
		
		/**计算行数*/
		getMaxRows:function(columns,rowIndex){
			var me=this;
			Ext.each(columns,function(column){
				if(column.dataIndex==null){
					var temp=me.getMaxRows(column.items.items,rowIndex+1);	
				}
			});
			if(rowIndex>me.maxrow){
				me.maxrow=rowIndex;
			}
			
			return me.maxrow;
		},

		columns:[],
		/***/
		headerCtl:[],
		/**最大行数*/
		maxrow:0,
		
		headings:'',
		
		stylesheetPath: 'ux/grid/gridPrinterCss/print.css',
		
		/**
		 * @property printAutomatically
		 * @type Boolean
		 * True to open the print dialog automatically and close the window after printing. False to simply open the print version
		 * of the grid (defaults to true)
		 */
		printAutomatically: true,
		
		

		/**
		 * @property bodyTpl
		 * @type {Object/Array} values
		 * The XTemplate used to create each row. This is used inside the 'print' function to build another XTemplate, to which the data
		 * are then applied (see the escaped dataIndex attribute here - this ends up as "{dataIndex}")
		 */
		bodyTpl: [
			'<tr>',
				'<tpl for=".">',
					'<td>\{{dataIndex}\}</td>',
				'</tpl>',
			'</tr>'
		]
	}
	
	
});