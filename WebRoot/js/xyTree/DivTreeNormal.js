(function() {
  if (!window.xyTree)
    window.xyTree = {};
})();

xyTree.TreeConfigNormal = {
  rootIcon        : './js/xyTree/images/foldericon.gif',
  openRootIcon    : './js/xyTree/images/openfoldericon.gif',
  folderIcon      : './js/xyTree/images/foldericon.gif',
  openFolderIcon  : './js/xyTree/images/openfoldericon.gif',
  fileIcon        : './js/xyTree/images/file.gif',
  iIcon           : './js/xyTree/images/I.gif',
  lIcon           : './js/xyTree/images/L.gif',
  lMinusIcon      : './js/xyTree/images/Lminus.gif',
  lPlusIcon       : './js/xyTree/images/Lplus.gif',
  tIcon           : './js/xyTree/images/T.gif',
  tMinusIcon      : './js/xyTree/images/Tminus.gif',
  tPlusIcon       : './js/xyTree/images/Tplus.gif',
  blankIcon       : './js/xyTree/images/blank.gif',
  defaultText     : 'Tree Item',
  defaultAction   : 'javascript:void(0);',
  defaultBehavior : 'classic',
  usePersistence	: true,
  jianju1         : '4px'
};
//锟斤拷锟揭伙拷锟饺拷直锟�2007/06/15
xyTree.defaultNodeClickAction ="xyTree.defaultNodeClickAction";//锟絬锟饺≈�



//div锟斤拷锟斤拷
//=====================================================================
xyTree.DivTreeNormal = function(name,img) {
  this.img;
  if(img) 
    this.img = img;
  
  var objectname = this.getName();
  this.tree = new xyTree.TreeNormal(name, objectname);
  this.tree.divtree = this;
  this.div = this.creatediv();
}

xyTree.DivTreeNormal.count = 0;

xyTree.DivTreeNormal.prototype.getName = function() {
  var s = "xytreenormalid" ;
  s += (window.xyTree.DivTreeNormal.count++);
  return s;
}


xyTree.DivTreeNormal.prototype.add = function(node) {
  this.tree.add(node);	
}

xyTree.DivTreeNormal.prototype.init = function(funClickNode, funClickRootNode) {
  var div = this.div.lastChild;
  //锟斤拷锟斤拷锟叫碉拷一锟斤拷锟节碉拷锟叫筹拷4
  //锟斤拷锟饺得碉拷锟斤拷锟叫碉拷一锟斤拷锟节碉拷
  var root = this.tree.root;
  var arr = root.child;
  for(var i = 0; i < arr.length; i++ )
    div.appendChild(arr[i].innerhtml());
  
  this.clickNode = funClickNode ? funClickNode : this.defaultClickNode;
  this.clickRootNode = funClickRootNode ? funClickRootNode : this.defaultClickRootNode;
}

xyTree.DivTreeNormal.prototype.creatediv = function (){
  var divtree = this;
  var div = document.createElement('div');
  div.className = 'treeyangshi';
  
  var divhead = document.createElement('div');
  var img = document.createElement('img');
  
  if (!this.img) {
    img.src = xyTree.TreeConfigNormal.openRootIcon;
    img.onclick = function() {
      var divbody = this.parentNode.parentNode.lastChild;
      if (divbody.style.display == 'block') {
        divbody.style.display = 'none';
        img.src = xyTree.TreeConfigNormal.rootIcon;
      } else {
        divbody.style.display = 'block';
        img.src = xyTree.TreeConfigNormal.openRootIcon;
      }
    }
  } else {
    img.src = this.img;
    img.className = 'treeyangshiImg';
    img.onclick = function() {
      var divbody = this.parentNode.parentNode.lastChild;
      if (divbody.style.display == 'block') {
        divbody.style.display = 'none';
        //img.src = xyTree.TreeConfigNormal.rootIcon;
      } else {
        divbody.style.display = 'block';
        //img.src = xyTree.TreeConfigNormal.openRootIcon;
      }
    }
  }
  
  img.align = "absbottom";
  divhead.appendChild(img);

  var qj = this.tree.objectname; //锟矫碉拷全锟街讹拷锟斤拷锟斤拷锟斤拷
  var a = document.createElement('a');
  a.href = 'javascript:void(0);';
  a.onclick = function() {
    divtree.clickRootNode();
  };
  a.onfocus = function() {this.blur();}
  a.appendChild(document.createTextNode(this.tree.treename));
  a.style.marginLeft = xyTree.TreeConfigNormal.jianju1;
  
  divhead.appendChild(a);
  div.appendChild(divhead);

  var divbody = document.createElement('div');
  divbody.style.display = 'block';
  div.appendChild(divbody);
  
  return div;
}



xyTree.DivTreeNormal.prototype.hideTreeBody = function() {
  this.div.lastChild.style.display = 'none';
  if (!this.img) {
    this.div.firstChild.firstChild.src = xyTree.TreeConfigNormal.rootIcon;
  }	  
}

xyTree.DivTreeNormal.prototype.showTreeBody = function() {
  this.div.lastChild.style.display = 'block';
  if (!this.img) {
    this.div.firstChild.firstChild.src = xyTree.TreeConfigNormal.openRootIcon;
  }	  
}


/**
 * 缺省锟侥碉拷锟斤拷诘锟斤拷锟斤拷为锟斤拷锟洁当锟节碉拷锟斤拷锟侥硷拷锟斤拷
 * @param {xyTree.Node} node 锟斤拷锟斤拷锟斤拷慕诘锟�
 */
xyTree.DivTreeNormal.prototype.defaultClickNode = function(node) {
  node.getHtmlElementFoldImg().onclick();
}

/**
 * 缺省锟侥碉拷锟斤拷锟节碉拷锟斤拷锟轿拷锟斤拷嗟憋拷诘锟斤拷锟斤拷诘愀囱★拷锟�
 */
xyTree.DivTreeNormal.prototype.defaultClickRootNode = function() {
  this.div.firstChild.firstChild.onclick();
}
