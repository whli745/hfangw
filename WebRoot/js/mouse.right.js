Element.addMethods({
     getNumStyle: function(element, style) {
       var value = $(element).getStyle(style);
       return value === null ? null : parseInt(value);
     }
   })
   document.observe('dom:loaded', function(){
     var myMenuItems = [
       {
         name: '后退',
         className: 'back',
         callback: function() {
           history.back();
         }
       },{
         name: '前进',
         className: 'advance',
         callback: function(e) {
           history.go(1);
         }
       },{
         separator: true
       },{
         name: '刷新',
         className: 'refresh ',
         callback: function() {
			location.href=location.href;
		 }
       },{
         name: '全屏',
         className: 'ShearCut',
         callback: function() {
           window.open(document.location,"url","fullscreen");
         }
       }
       /*,{
         name: '页面属性',
         className: 'copy',
         callback: function() {
           Dialog.property(location);
         }
       }*/
     ]
       new Proto.Menu({
       selector: '#mouseRight',
       className: 'menu desktop',
       menuItems: myMenuItems
     })
   })