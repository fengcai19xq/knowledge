
setTimeout(function() {
			Ext.get('loading').remove();
			Ext.get('loading-mask').fadeOut({
						remove : true
					});
		}, 1000);
Ext.onReady(function() {
	//Ext.QuickTips.init();
	Ext.create('Ext.Viewport', {
				layout : {
					type : 'border'
				},
				width : document.documentElement.clientWidth,
				height : document.documentElement.clientHeight,
				items : [demoForm, demoGrid]
			});
});