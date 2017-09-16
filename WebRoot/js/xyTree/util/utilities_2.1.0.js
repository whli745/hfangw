/* Copyright (c) 2006, Yahoo! Inc. All rights reserved.Code licensed under the BSD License:http://developer.yahoo.net/yui/license.txt */
if (typeof YAHOO == "undefined") {
	var YAHOO = {};
}
YAHOO.namespace = function () {
	var a = arguments,
		o = null,
		i, j, d;
	for (i = 0; i < a.length; ++i) {
		d = a[i].split(".");
		o = YAHOO;
		for (j = (d[0] == "YAHOO") ? 1 : 0; j < d.length; ++j) {
			o[d[j]] = o[d[j]] || {};
			o = o[d[j]];
		}
	}
	return o;
};
YAHOO.log = function (_2, _3, _4) {
	var l = YAHOO.widget.Logger;
	if (l && l.log) {
		return l.log(_2, _3, _4);
	} else {
		return false;
	}
};
YAHOO.extend = function (_6, _7, _8) {
	var F = function () {};
	F.prototype = _7.prototype;
	_6.prototype = new F();
	_6.prototype.constructor = _6;
	_6.superclass = _7.prototype;
	if (_7.prototype.constructor == Object.prototype.constructor) {
		_7.prototype.constructor = _7;
	}
	if (_8) {
		for (var i in _8) {
			_6.prototype[i] = _8[i];
		}
	}
};
YAHOO.augment = function (r, s) {
	var rp = r.prototype,
		sp = s.prototype,
		a = arguments,
		i, p;
	if (a[2]) {
		for (i = 2; i < a.length; ++i) {
			rp[a[i]] = sp[a[i]];
		}
	} else {
		for (p in sp) {
			if (!rp[p]) {
				rp[p] = sp[p];
			}
		}
	}
};
YAHOO.namespace("util", "widget", "example");
(function () {
	var Y = YAHOO.util,
		getStyle, setStyle, id_counter = 0,
		propertyCache = {};
	var ua = navigator.userAgent.toLowerCase(),
		isOpera = (ua.indexOf('opera') > -1),
		isSafari = (ua.indexOf('safari') > -1),
		isGecko = (!isOpera && !isSafari && ua.indexOf('gecko') > -1),
		isIE = (!isOpera && ua.indexOf('msie') > -1);
	var patterns = {
		HYPHEN: /(-[a-z])/i
	};
	var toCamel = function (property) {
		if (!patterns.HYPHEN.test(property)) {
			return property;
		}
		if (propertyCache[property]) {
			return propertyCache[property];
		}
		while (patterns.HYPHEN.exec(property)) {
			property = property.replace(RegExp.$1, RegExp.$1.substr(1).toUpperCase());
		}
		propertyCache[property] = property;
		return property;
	};
	if (document.defaultView && document.defaultView.getComputedStyle) {
		getStyle = function (el, property) {
			var value = null;
			var computed = document.defaultView.getComputedStyle(el, '');
			if (computed) {
				value = computed[toCamel(property)];
			}
			return el.style[property] || value;
		};
	} else if (document.documentElement.currentStyle && isIE) {
		getStyle = function (el, property) {
			switch (toCamel(property)) {
			case 'opacity':
				var val = 100;
				try {
					val = el.filters['DXImageTransform.Microsoft.Alpha'].opacity;
				} catch (e) {
					try {
						val = el.filters('alpha').opacity;
					} catch (e) {}
				}
				return val / 100;
				break;
			default:
				var value = el.currentStyle ? el.currentStyle[property] : null;
				return (el.style[property] || value);
			}
		};
	} else {
		getStyle = function (el, property) {
			return el.style[property];
		};
	}
	if (isIE) {
		setStyle = function (el, property, val) {
			switch (property) {
			case 'opacity':
				if (typeof el.style.filter == 'string') {
					el.style.filter = 'alpha(opacity=' + val * 100 + ')';
					if (!el.currentStyle || !el.currentStyle.hasLayout) {
						el.style.zoom = 1;
					}
				}
				break;
			default:
				el.style[property] = val;
			}
		};
	} else {
		setStyle = function (el, property, val) {
			el.style[property] = val;
		};
	}
	YAHOO.util.Dom = {
		get: function (el) {
			if (!el) {
				return null;
			}
			if (typeof el != 'string' && !(el instanceof Array)) {
				return el;
			}
			if (typeof el == 'string') {
				return document.getElementById(el);
			} else {
				var collection = [];
				for (var i = 0, len = el.length; i < len; ++i) {
					collection[collection.length] = Y.Dom.get(el[i]);
				}
				return collection;
			}
			return null;
		},
		getStyle: function (el, property) {
			property = toCamel(property);
			var f = function (element) {
				return getStyle(element, property);
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		setStyle: function (el, property, val) {
			property = toCamel(property);
			var f = function (element) {
				setStyle(element, property, val);
			};
			Y.Dom.batch(el, f, Y.Dom, true);
		},
		getXY: function (el) {
			var f = function (el) {
				if (el.parentNode === null || el.offsetParent === null || this.getStyle(el, 'display') == 'none') {
					return false;
				}
				var parentNode = null;
				var pos = [];
				var box;
				if (el.getBoundingClientRect) {
					box = el.getBoundingClientRect();
					var doc = document;
					if (!this.inDocument(el) && parent.document != document) {
						doc = parent.document;
						if (!this.isAncestor(doc.documentElement, el)) {
							return false;
						}
					}
					var scrollTop = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
					var scrollLeft = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
					return [box.left + scrollLeft, box.top + scrollTop];
				} else {
					pos = [el.offsetLeft, el.offsetTop];
					parentNode = el.offsetParent;
					if (parentNode != el) {
						while (parentNode) {
							pos[0] += parentNode.offsetLeft;
							pos[1] += parentNode.offsetTop;
							parentNode = parentNode.offsetParent;
						}
					}
					if (isSafari && this.getStyle(el, 'position') == 'absolute') {
						pos[0] -= document.body.offsetLeft;
						pos[1] -= document.body.offsetTop;
					}
				}
				if (el.parentNode) {
					parentNode = el.parentNode;
				} else {
					parentNode = null;
				}
				while (parentNode && parentNode.tagName.toUpperCase() != 'BODY' && parentNode.tagName.toUpperCase() != 'HTML') {
					if (Y.Dom.getStyle(parentNode, 'display') != 'inline') {
						pos[0] -= parentNode.scrollLeft;
						pos[1] -= parentNode.scrollTop;
					}
					if (parentNode.parentNode) {
						parentNode = parentNode.parentNode;
					} else {
						parentNode = null;
					}
				}
				return pos;
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		getX: function (el) {
			var f = function (el) {
				return Y.Dom.getXY(el)[0];
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		getY: function (el) {
			var f = function (el) {
				return Y.Dom.getXY(el)[1];
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		setXY: function (el, pos, noRetry) {
			var f = function (el) {
				var style_pos = this.getStyle(el, 'position');
				if (style_pos == 'static') {
					this.setStyle(el, 'position', 'relative');
					style_pos = 'relative';
				}
				var pageXY = this.getXY(el);
				if (pageXY === false) {
					return false;
				}
				var delta = [parseInt(this.getStyle(el, 'left'), 10), parseInt(this.getStyle(el, 'top'), 10)];
				if (isNaN(delta[0])) {
					delta[0] = (style_pos == 'relative') ? 0 : el.offsetLeft;
				}
				if (isNaN(delta[1])) {
					delta[1] = (style_pos == 'relative') ? 0 : el.offsetTop;
				}
				if (pos[0] !== null) {
					el.style.left = pos[0] - pageXY[0] + delta[0] + 'px';
				}
				if (pos[1] !== null) {
					el.style.top = pos[1] - pageXY[1] + delta[1] + 'px';
				}
				var newXY = this.getXY(el);
				if (!noRetry && (newXY[0] != pos[0] || newXY[1] != pos[1])) {
					this.setXY(el, pos, true);
				}
			};
			Y.Dom.batch(el, f, Y.Dom, true);
		},
		setX: function (el, x) {
			Y.Dom.setXY(el, [x, null]);
		},
		setY: function (el, y) {
			Y.Dom.setXY(el, [null, y]);
		},
		getRegion: function (el) {
			var f = function (el) {
				var region = new Y.Region.getRegion(el);
				return region;
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		getClientWidth: function () {
			return Y.Dom.getViewportWidth();
		},
		getClientHeight: function () {
			return Y.Dom.getViewportHeight();
		},
		getElementsByClassName: function (className, tag, root) {
			var method = function (el) {
				return Y.Dom.hasClass(el, className);
			};
			return Y.Dom.getElementsBy(method, tag, root);
		},
		hasClass: function (el, className) {
			var re = new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)');
			var f = function (el) {
				return re.test(el['className']);
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		addClass: function (el, className) {
			var f = function (el) {
				if (this.hasClass(el, className)) {
					return;
				}
				el['className'] = [el['className'], className].join(' ');
			};
			Y.Dom.batch(el, f, Y.Dom, true);
		},
		removeClass: function (el, className) {
			var re = new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)', 'g');
			var f = function (el) {
				if (!this.hasClass(el, className)) {
					return;
				}
				var c = el['className'];
				el['className'] = c.replace(re, ' ');
				if (this.hasClass(el, className)) {
					this.removeClass(el, className);
				}
			};
			Y.Dom.batch(el, f, Y.Dom, true);
		},
		replaceClass: function (el, oldClassName, newClassName) {
			if (oldClassName === newClassName) {
				return false;
			}
			var re = new RegExp('(?:^|\\s+)' + oldClassName + '(?:\\s+|$)', 'g');
			var f = function (el) {
				if (!this.hasClass(el, oldClassName)) {
					this.addClass(el, newClassName);
					return;
				}
				el['className'] = el['className'].replace(re, ' ' + newClassName + ' ');
				if (this.hasClass(el, oldClassName)) {
					this.replaceClass(el, oldClassName, newClassName);
				}
			};
			Y.Dom.batch(el, f, Y.Dom, true);
		},
		generateId: function (el, prefix) {
			prefix = prefix || 'yui-gen';
			el = el || {};
			var f = function (el) {
				if (el) {
					el = Y.Dom.get(el);
				} else {
					el = {};
				}
				if (!el.id) {
					el.id = prefix + id_counter++;
				}
				return el.id;
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		isAncestor: function (haystack, needle) {
			haystack = Y.Dom.get(haystack);
			if (!haystack || !needle) {
				return false;
			}
			var f = function (needle) {
				if (haystack.contains && !isSafari) {
					return haystack.contains(needle);
				} else if (haystack.compareDocumentPosition) {
					return !!(haystack.compareDocumentPosition(needle) & 16);
				} else {
					var parent = needle.parentNode;
					while (parent) {
						if (parent == haystack) {
							return true;
						} else if (!parent.tagName || parent.tagName.toUpperCase() == 'HTML') {
							return false;
						}
						parent = parent.parentNode;
					}
					return false;
				}
			};
			return Y.Dom.batch(needle, f, Y.Dom, true);
		},
		inDocument: function (el) {
			var f = function (el) {
				return this.isAncestor(document.documentElement, el);
			};
			return Y.Dom.batch(el, f, Y.Dom, true);
		},
		getElementsBy: function (method, tag, root) {
			tag = tag || '*';
			root = Y.Dom.get(root) || document;
			var nodes = [];
			var elements = root.getElementsByTagName(tag);
			if (!elements.length && (tag == '*' && root.all)) {
				elements = root.all;
			}
			for (var i = 0, len = elements.length; i < len; ++i) {
				if (method(elements[i])) {
					nodes[nodes.length] = elements[i];
				}
			}
			return nodes;
		},
		batch: function (el, method, o, override) {
			var id = el;
			el = Y.Dom.get(el);
			var scope = (override) ? o : window;
			if (!el || el.tagName || !el.length) {
				if (!el) {
					return false;
				}
				return method.call(scope, el, o);
			}
			var collection = [];
			for (var i = 0, len = el.length; i < len; ++i) {
				if (!el[i]) {
					id = el[i];
				}
				collection[collection.length] = method.call(scope, el[i], o);
			}
			return collection;
		},
		getDocumentHeight: function () {
			var scrollHeight = (document.compatMode != 'CSS1Compat') ? document.body.scrollHeight : document.documentElement.scrollHeight;
			var h = Math.max(scrollHeight, Y.Dom.getViewportHeight());
			return h;
		},
		getDocumentWidth: function () {
			var scrollWidth = (document.compatMode != 'CSS1Compat') ? document.body.scrollWidth : document.documentElement.scrollWidth;
			var w = Math.max(scrollWidth, Y.Dom.getViewportWidth());
			return w;
		},
		getViewportHeight: function () {
			var height = self.innerHeight;
			var mode = document.compatMode;
			if ((mode || isIE) && !isOpera) {
				//height = (mode == 'CSS1Compat') ? document.documentElement.clientHeight : document.body.clientHeight;
				height = document.body.clientHeight;
			}
			return height;
		},
		getViewportWidth: function () {
			var width = self.innerWidth;
			var mode = document.compatMode;
			if (mode || isIE) {
				width = (mode == 'CSS1Compat') ? document.documentElement.clientWidth : document.body.clientWidth;
			}
			return width;
		}
	};
})();
YAHOO.util.Region = function (t, r, b, l) {
	this.top = t;
	this[1] = t;
	this.right = r;
	this.bottom = b;
	this.left = l;
	this[0] = l;
};
YAHOO.util.Region.prototype.contains = function (region) {
	return (region.left >= this.left && region.right <= this.right && region.top >= this.top && region.bottom <= this.bottom);
};
YAHOO.util.Region.prototype.getArea = function () {
	return ((this.bottom - this.top) * (this.right - this.left));
};
YAHOO.util.Region.prototype.intersect = function (region) {
	var t = Math.max(this.top, region.top);
	var r = Math.min(this.right, region.right);
	var b = Math.min(this.bottom, region.bottom);
	var l = Math.max(this.left, region.left);
	if (b >= t && r >= l) {
		return new YAHOO.util.Region(t, r, b, l);
	} else {
		return null;
	}
};
YAHOO.util.Region.prototype.union = function (region) {
	var t = Math.min(this.top, region.top);
	var r = Math.max(this.right, region.right);
	var b = Math.max(this.bottom, region.bottom);
	var l = Math.min(this.left, region.left);
	return new YAHOO.util.Region(t, r, b, l);
};
YAHOO.util.Region.prototype.toString = function () {
	return ("Region {" + "top: " + this.top + ", right: " + this.right + ", bottom: " + this.bottom + ", left: " + this.left + "}");
};
YAHOO.util.Region.getRegion = function (el) {
	var p = YAHOO.util.Dom.getXY(el);
	var t = p[1];
	var r = p[0] + el.offsetWidth;
	var b = p[1] + el.offsetHeight;
	var l = p[0];
	return new YAHOO.util.Region(t, r, b, l);
};
YAHOO.util.Point = function (x, y) {
	if (x instanceof Array) {
		y = x[1];
		x = x[0];
	}
	this.x = this.right = this.left = this[0] = x;
	this.y = this.top = this.bottom = this[1] = y;
};
YAHOO.util.Point.prototype = new YAHOO.util.Region();
YAHOO.util.CustomEvent = function (_1, _2, _3, _4) {
	this.type = _1;
	this.scope = _2 || window;
	this.silent = _3;
	this.signature = _4 || YAHOO.util.CustomEvent.LIST;
	this.subscribers = [];
	if (!this.silent) {}
	var _5 = "_YUICEOnSubscribe";
	if (_1 !== _5) {
		this.subscribeEvent = new YAHOO.util.CustomEvent(_5, this, true);
	}
};
YAHOO.util.CustomEvent.LIST = 0;
YAHOO.util.CustomEvent.FLAT = 1;
YAHOO.util.CustomEvent.prototype = {
	subscribe: function (fn, _7, _8) {
		if (this.subscribeEvent) {
			this.subscribeEvent.fire(fn, _7, _8);
		}
		this.subscribers.push(new YAHOO.util.Subscriber(fn, _7, _8));
	},
	unsubscribe: function (fn, _9) {
		var _10 = false;
		for (var i = 0, len = this.subscribers.length; i < len; ++i) {
			var s = this.subscribers[i];
			if (s && s.contains(fn, _9)) {
				this._delete(i);
				_10 = true;
			}
		}
		return _10;
	},
	fire: function () {
		var len = this.subscribers.length;
		if (!len && this.silent) {
			return true;
		}
		var _14 = [],
			ret = true,
			i;
		for (i = 0; i < arguments.length; ++i) {
			_14.push(arguments[i]);
		}
		var _15 = _14.length;
		if (!this.silent) {}
		for (i = 0; i < len; ++i) {
			var s = this.subscribers[i];
			if (s) {
				if (!this.silent) {}
				var _16 = s.getScope(this.scope);
				if (this.signature == YAHOO.util.CustomEvent.FLAT) {
					var _17 = null;
					if (_14.length > 0) {
						_17 = _14[0];
					}
					ret = s.fn.call(_16, _17, s.obj);
				} else {
					ret = s.fn.call(_16, this.type, _14, s.obj);
				}
				if (false === ret) {
					if (!this.silent) {}
					return false;
				}
			}
		}
		return true;
	},
	unsubscribeAll: function () {
		for (var i = 0, len = this.subscribers.length; i < len; ++i) {
			this._delete(len - 1 - i);
		}
	},
	_delete: function (_18) {
		var s = this.subscribers[_18];
		if (s) {
			delete s.fn;
			delete s.obj;
		}
		this.subscribers.splice(_18, 1);
	},
	toString: function () {
		return "CustomEvent: " + "'" + this.type + "', " + "scope: " + this.scope;
	}
};
YAHOO.util.Subscriber = function (fn, obj, _20) {
	this.fn = fn;
	this.obj = obj || null;
	this.override = _20;
};
YAHOO.util.Subscriber.prototype.getScope = function (_21) {
	if (this.override) {
		if (this.override === true) {
			return this.obj;
		} else {
			return this.override;
		}
	}
	return _21;
};
YAHOO.util.Subscriber.prototype.contains = function (fn, obj) {
	if (obj) {
		return (this.fn == fn && this.obj == obj);
	} else {
		return (this.fn == fn);
	}
};
YAHOO.util.Subscriber.prototype.toString = function () {
	return "Subscriber { obj: " + (this.obj || "") + ", override: " + (this.override || "no") + " }";
};
if (!YAHOO.util.Event) {
	YAHOO.util.Event = function () {
		var _22 = false;
		var _23 = [];
		var _24 = [];
		var _25 = [];
		var _26 = [];
		var _27 = 0;
		var _28 = [];
		var _29 = [];
		var _30 = 0;
		return {
			POLL_RETRYS: 200,
			POLL_INTERVAL: 20,
			EL: 0,
			TYPE: 1,
			FN: 2,
			WFN: 3,
			OBJ: 3,
			ADJ_SCOPE: 4,
			isSafari: (/Safari|Konqueror|KHTML/gi).test(navigator.userAgent),
			isIE: (!this.isSafari && !navigator.userAgent.match(/opera/gi) && navigator.userAgent.match(/msie/gi)),
			_interval: null,
			startInterval: function () {
				if (!this._interval) {
					var _31 = this;
					var _32 = function () {
						_31._tryPreloadAttach();
					};
					this._interval = setInterval(_32, this.POLL_INTERVAL);
				}
			},
			onAvailable: function (_33, _34, _35, _36) {
				_28.push({
					id: _33,
					fn: _34,
					obj: _35,
					override: _36,
					checkReady: false
				});
				_27 = this.POLL_RETRYS;
				this.startInterval();
			},
			onContentReady: function (_37, _38, _39, _40) {
				_28.push({
					id: _37,
					fn: _38,
					obj: _39,
					override: _40,
					checkReady: true
				});
				_27 = this.POLL_RETRYS;
				this.startInterval();
			},
			addListener: function (el, _42, fn, obj, _43) {
				if (!fn || !fn.call) {
					return false;
				}
				if (this._isValidCollection(el)) {
					var ok = true;
					for (var i = 0, len = el.length; i < len; ++i) {
						ok = this.on(el[i], _42, fn, obj, _43) && ok;
					}
					return ok;
				} else {
					if (typeof el == "string") {
						var oEl = this.getEl(el);
						if (oEl) {
							el = oEl;
						} else {
							this.onAvailable(el, function () {
								YAHOO.util.Event.on(el, _42, fn, obj, _43);
							});
							return true;
						}
					}
				}
				if (!el) {
					return false;
				}
				if ("unload" == _42 && obj !== this) {
					_24[_24.length] = [el, _42, fn, obj, _43];
					return true;
				}
				var _46 = el;
				if (_43) {
					if (_43 === true) {
						_46 = obj;
					} else {
						_46 = _43;
					}
				}
				var _47 = function (e) {
					return fn.call(_46, YAHOO.util.Event.getEvent(e), obj);
				};
				var li = [el, _42, fn, _47, _46];
				var _50 = _23.length;
				_23[_50] = li;
				if (this.useLegacyEvent(el, _42)) {
					var _51 = this.getLegacyIndex(el, _42);
					if (_51 == -1 || el != _25[_51][0]) {
						_51 = _25.length;
						_29[el.id + _42] = _51;
						_25[_51] = [el, _42, el["on" + _42]];
						_26[_51] = [];
						el["on" + _42] = function (e) {
							YAHOO.util.Event.fireLegacyEvent(YAHOO.util.Event.getEvent(e), _51);
						};
					}
					_26[_51].push(li);
				} else {
					this._simpleAdd(el, _42, _47, false);
				}
				return true;
			},
			fireLegacyEvent: function (e, _52) {
				var ok = true;
				var le = _26[_52];
				for (var i = 0, len = le.length; i < len; ++i) {
					var li = le[i];
					if (li && li[this.WFN]) {
						var _54 = li[this.ADJ_SCOPE];
						var ret = li[this.WFN].call(_54, e);
						ok = (ok && ret);
					}
				}
				return ok;
			},
			getLegacyIndex: function (el, _56) {
				var key = this.generateId(el) + _56;
				if (typeof _29[key] == "undefined") {
					return -1;
				} else {
					return _29[key];
				}
			},
			useLegacyEvent: function (el, _58) {
				if (!el.addEventListener && !el.attachEvent) {
					return true;
				} else {
					if (this.isSafari) {
						if ("click" == _58 || "dblclick" == _58) {
							return true;
						}
					}
				}
				return false;
			},
			removeListener: function (el, _59, fn) {
				var i, len;
				if (typeof el == "string") {
					el = this.getEl(el);
				} else {
					if (this._isValidCollection(el)) {
						var ok = true;
						for (i = 0, len = el.length; i < len; ++i) {
							ok = (this.removeListener(el[i], _59, fn) && ok);
						}
						return ok;
					}
				}
				if (!fn || !fn.call) {
					return this.purgeElement(el, false, _59);
				}
				if ("unload" == _59) {
					for (i = 0, len = _24.length; i < len; i++) {
						var li = _24[i];
						if (li && li[0] == el && li[1] == _59 && li[2] == fn) {
							_24.splice(i, 1);
							return true;
						}
					}
					return false;
				}
				var _60 = null;
				var _61 = arguments[3];
				if ("undefined" == typeof _61) {
					_61 = this._getCacheIndex(el, _59, fn);
				}
				if (_61 >= 0) {
					_60 = _23[_61];
				}
				if (!el || !_60) {
					return false;
				}
				if (this.useLegacyEvent(el, _59)) {
					var _62 = this.getLegacyIndex(el, _59);
					var _63 = _26[_62];
					if (_63) {
						for (i = 0, len = _63.length; i < len; ++i) {
							li = _63[i];
							if (li && li[this.EL] == el && li[this.TYPE] == _59 && li[this.FN] == fn) {
								_63.splice(i, 1);
							}
						}
					}
				} else {
					this._simpleRemove(el, _59, _60[this.WFN], false);
				}
				delete _23[_61][this.WFN];
				delete _23[_61][this.FN];
				_23.splice(_61, 1);
				return true;
			},
			getTarget: function (ev, _65) {
				var t = ev.target || ev.srcElement;
				return this.resolveTextNode(t);
			},
			resolveTextNode: function (_67) {
				if (_67 && 3 == _67.nodeType) {
					return _67.parentNode;
				} else {
					return _67;
				}
			},
			getPageX: function (ev) {
				var x = ev.pageX;
				if (!x && 0 !== x) {
					x = ev.clientX || 0;
					if (this.isIE) {
						x += this._getScrollLeft();
					}
				}
				return x;
			},
			getPageY: function (ev) {
				var y = ev.pageY;
				if (!y && 0 !== y) {
					y = ev.clientY || 0;
					if (this.isIE) {
						y += this._getScrollTop();
					}
				}
				return y;
			},
			getXY: function (ev) {
				return [this.getPageX(ev), this.getPageY(ev)];
			},
			getRelatedTarget: function (ev) {
				var t = ev.relatedTarget;
				if (!t) {
					if (ev.type == "mouseout") {
						t = ev.toElement;
					} else {
						if (ev.type == "mouseover") {
							t = ev.fromElement;
						}
					}
				}
				return this.resolveTextNode(t);
			},
			getTime: function (ev) {
				if (!ev.time) {
					var t = new Date().getTime();
					try {
						ev.time = t;
					} catch (e) {
						return t;
					}
				}
				return ev.time;
			},
			stopEvent: function (ev) {
				this.stopPropagation(ev);
				this.preventDefault(ev);
			},
			stopPropagation: function (ev) {
				if (ev.stopPropagation) {
					ev.stopPropagation();
				} else {
					ev.cancelBubble = true;
				}
			},
			preventDefault: function (ev) {
				if (ev.preventDefault) {
					ev.preventDefault();
				} else {
					ev.returnValue = false;
				}
			},
			getEvent: function (e) {
				var ev = e || window.event;
				if (!ev) {
					var c = this.getEvent.caller;
					while (c) {
						ev = c.arguments[0];
						if (ev && Event == ev.constructor) {
							break;
						}
						c = c.caller;
					}
				}
				return ev;
			},
			getCharCode: function (ev) {
				return ev.charCode || ev.keyCode || 0;
			},
			_getCacheIndex: function (el, _71, fn) {
				for (var i = 0, len = _23.length; i < len; ++i) {
					var li = _23[i];
					if (li && li[this.FN] == fn && li[this.EL] == el && li[this.TYPE] == _71) {
						return i;
					}
				}
				return -1;
			},
			generateId: function (el) {
				var id = el.id;
				if (!id) {
					id = "yuievtautoid-" + _30;
					++_30;
					el.id = id;
				}
				return id;
			},
			_isValidCollection: function (o) {
				return (o && o.length && typeof o != "string" && !o.tagName && !o.alert && typeof o[0] != "undefined");
			},
			elCache: {},
			getEl: function (id) {
				return document.getElementById(id);
			},
			clearCache: function () {},
			_load: function (e) {
				_22 = true;
				var EU = YAHOO.util.Event;
				if (this.isIE) {
					EU._simpleRemove(window, "load", EU._load);
				}
			},
			_tryPreloadAttach: function () {
				if (this.locked) {
					return false;
				}
				this.locked = true;
				var _75 = !_22;
				if (!_75) {
					_75 = (_27 > 0);
				}
				var _76 = [];
				for (var i = 0, len = _28.length; i < len; ++i) {
					var _77 = _28[i];
					if (_77) {
						var el = this.getEl(_77.id);
						if (el) {
							if (!_77.checkReady || _22 || el.nextSibling || (document && document.body)) {
								var _78 = el;
								if (_77.override) {
									if (_77.override === true) {
										_78 = _77.obj;
									} else {
										_78 = _77.override;
									}
								}
								_77.fn.call(_78, _77.obj);
								delete _28[i];
							}
						} else {
							_76.push(_77);
						}
					}
				}
				_27 = (_76.length === 0) ? 0 : _27 - 1;
				if (_75) {
					this.startInterval();
				} else {
					clearInterval(this._interval);
					this._interval = null;
				}
				this.locked = false;
				return true;
			},
			purgeElement: function (el, _79, _80) {
				var _81 = this.getListeners(el, _80);
				if (_81) {
					for (var i = 0, len = _81.length; i < len; ++i) {
						var l = _81[i];
						this.removeListener(el, l.type, l.fn);
					}
				}
				if (_79 && el && el.childNodes) {
					for (i = 0, len = el.childNodes.length; i < len; ++i) {
						this.purgeElement(el.childNodes[i], _79, _80);
					}
				}
			},
			getListeners: function (el, _83) {
				var _84 = [];
				if (_23 && _23.length > 0) {
					for (var i = 0, len = _23.length; i < len; ++i) {
						var l = _23[i];
						if (l && l[this.EL] === el && (!_83 || _83 === l[this.TYPE])) {
							_84.push({
								type: l[this.TYPE],
								fn: l[this.FN],
								obj: l[this.OBJ],
								adjust: l[this.ADJ_SCOPE],
								index: i
							});
						}
					}
				}
				return (_84.length) ? _84 : null;
			},
			_unload: function (e) {
				var EU = YAHOO.util.Event,
					i, j, l, len, index;
				for (i = 0, len = _24.length; i < len; ++i) {
					l = _24[i];
					if (l) {
						var _85 = window;
						if (l[EU.ADJ_SCOPE]) {
							if (l[EU.ADJ_SCOPE] === true) {
								_85 = l[EU.OBJ];
							} else {
								_85 = l[EU.ADJ_SCOPE];
							}
						}
						l[EU.FN].call(_85, EU.getEvent(e), l[EU.OBJ]);
						delete _24[i];
						l = null;
						_85 = null;
					}
				}
				if (_23 && _23.length > 0) {
					j = _23.length;
					while (j) {
						index = j - 1;
						l = _23[index];
						if (l) {
							EU.removeListener(l[EU.EL], l[EU.TYPE], l[EU.FN], index);
						}
						j = j - 1;
					}
					l = null;
					EU.clearCache();
				}
				for (i = 0, len = _25.length; i < len; ++i) {
					delete _25[i][0];
					delete _25[i];
				}
				EU._simpleRemove(window, "unload", EU._unload);
			},
			_getScrollLeft: function () {
				return this._getScroll()[1];
			},
			_getScrollTop: function () {
				return this._getScroll()[0];
			},
			_getScroll: function () {
				var dd = document.documentElement,
					db = document.body;
				if (dd && (dd.scrollTop || dd.scrollLeft)) {
					return [dd.scrollTop, dd.scrollLeft];
				} else {
					if (db) {
						return [db.scrollTop, db.scrollLeft];
					} else {
						return [0, 0];
					}
				}
			},
			_simpleAdd: function () {
				if (window.addEventListener) {
					return function (el, _87, fn, _88) {
						el.addEventListener(_87, fn, (_88));
					};
				} else {
					if (window.attachEvent) {
						return function (el, _89, fn, _90) {
							el.attachEvent("on" + _89, fn);
						};
					} else {
						return function () {};
					}
				}
			}(),
			_simpleRemove: function () {
				if (window.removeEventListener) {
					return function (el, _91, fn, _92) {
						el.removeEventListener(_91, fn, (_92));
					};
				} else {
					if (window.detachEvent) {
						return function (el, _93, fn) {
							el.detachEvent("on" + _93, fn);
						};
					} else {
						return function () {};
					}
				}
			}()
		};
	}();
	(function () {
		var EU = YAHOO.util.Event;
		EU.on = EU.addListener;
		if (document && document.body) {
			EU._load();
		} else {
			EU._simpleAdd(window, "load", EU._load);
		}
		EU._simpleAdd(window, "unload", EU._unload);
		EU._tryPreloadAttach();
	})();
}
YAHOO.util.EventProvider = function () {};
YAHOO.util.EventProvider.prototype = {
	__yui_events: null,
	__yui_subscribers: null,
	subscribe: function (_94, _95, _96, _97) {
		this.__yui_events = this.__yui_events || {};
		var ce = this.__yui_events[_94];
		if (ce) {
			ce.subscribe(_95, _96, _97);
		} else {
			this.__yui_subscribers = this.__yui_subscribers || {};
			var _99 = this.__yui_subscribers;
			if (!_99[_94]) {
				_99[_94] = [];
			}
			_99[_94].push({
				fn: _95,
				obj: _96,
				override: _97
			});
		}
	},
	unsubscribe: function (_100, p_fn, _102) {
		this.__yui_events = this.__yui_events || {};
		var ce = this.__yui_events[_100];
		if (ce) {
			return ce.unsubscribe(p_fn, _102);
		} else {
			return false;
		}
	},
	createEvent: function (_103, _104) {
		this.__yui_events = this.__yui_events || {};
		var opts = _104 || {};
		var _106 = this.__yui_events;
		if (_106[_103]) {} else {
			var _107 = opts.scope || this;
			var _108 = opts.silent || null;
			var ce = new YAHOO.util.CustomEvent(_103, _107, _108, YAHOO.util.CustomEvent.FLAT);
			_106[_103] = ce;
			if (opts.onSubscribeCallback) {
				ce.subscribeEvent.subscribe(opts.onSubscribeCallback);
			}
			this.__yui_subscribers = this.__yui_subscribers || {};
			var qs = this.__yui_subscribers[_103];
			if (qs) {
				for (var i = 0; i < qs.length; ++i) {
					ce.subscribe(qs[i].fn, qs[i].obj, qs[i].override);
				}
			}
		}
		return _106[_103];
	},
	fireEvent: function (_110, arg1, arg2, etc) {
		this.__yui_events = this.__yui_events || {};
		var ce = this.__yui_events[_110];
		if (ce) {
			var args = [];
			for (var i = 1; i < arguments.length; ++i) {
				args.push(arguments[i]);
			}
			return ce.fire.apply(ce, args);
		} else {
			return null;
		}
	},
	hasEvent: function (type) {
		if (this.__yui_events) {
			if (this.__yui_events[type]) {
				return true;
			}
		}
		return false;
	}
};
YAHOO.util.Anim = function (el, attributes, duration, method) {
	if (el) {
		this.init(el, attributes, duration, method);
	}
};
YAHOO.util.Anim.prototype = {
	toString: function () {
		var el = this.getEl();
		var id = el.id || el.tagName;
		return ("Anim " + id);
	},
	patterns: {
		noNegatives: /width|height|opacity|padding/i,
		offsetAttribute: /^((width|height)|(top|left))$/,
		defaultUnit: /width|height|top$|bottom$|left$|right$/i,
		offsetUnit: /\d+(em|%|en|ex|pt|in|cm|mm|pc)$/i
	},
	doMethod: function (attr, start, end) {
		return this.method(this.currentFrame, start, end - start, this.totalFrames);
	},
	setAttribute: function (attr, val, unit) {
		if (this.patterns.noNegatives.test(attr)) {
			val = (val > 0) ? val : 0;
		}
		YAHOO.util.Dom.setStyle(this.getEl(), attr, val + unit);
	},
	getAttribute: function (attr) {
		var el = this.getEl();
		var val = YAHOO.util.Dom.getStyle(el, attr);
		if (val !== 'auto' && !this.patterns.offsetUnit.test(val)) {
			return parseFloat(val);
		}
		var a = this.patterns.offsetAttribute.exec(attr) || [];
		var pos = !! (a[3]);
		var box = !! (a[2]);
		if (box || (YAHOO.util.Dom.getStyle(el, 'position') == 'absolute' && pos)) {
			val = el['offset' + a[0].charAt(0).toUpperCase() + a[0].substr(1)];
		} else {
			val = 0;
		}
		return val;
	},
	getDefaultUnit: function (attr) {
		if (this.patterns.defaultUnit.test(attr)) {
			return 'px';
		}
		return '';
	},
	setRuntimeAttribute: function (attr) {
		var start;
		var end;
		var attributes = this.attributes;
		this.runtimeAttributes[attr] = {};
		var isset = function (prop) {
			return (typeof prop !== 'undefined');
		};
		if (!isset(attributes[attr]['to']) && !isset(attributes[attr]['by'])) {
			return false;
		}
		start = (isset(attributes[attr]['from'])) ? attributes[attr]['from'] : this.getAttribute(attr);
		if (isset(attributes[attr]['to'])) {
			end = attributes[attr]['to'];
		} else if (isset(attributes[attr]['by'])) {
			if (start.constructor == Array) {
				end = [];
				for (var i = 0, len = start.length; i < len; ++i) {
					end[i] = start[i] + attributes[attr]['by'][i];
				}
			} else {
				end = start + attributes[attr]['by'];
			}
		}
		this.runtimeAttributes[attr].start = start;
		this.runtimeAttributes[attr].end = end;
		this.runtimeAttributes[attr].unit = (isset(attributes[attr].unit)) ? attributes[attr]['unit'] : this.getDefaultUnit(attr);
	},
	init: function (el, attributes, duration, method) {
		var isAnimated = false;
		var startTime = null;
		var actualFrames = 0;
		el = YAHOO.util.Dom.get(el);
		this.attributes = attributes || {};
		this.duration = duration || 1;
		this.method = method || YAHOO.util.Easing.easeNone;
		this.useSeconds = true;
		this.currentFrame = 0;
		this.totalFrames = YAHOO.util.AnimMgr.fps;
		this.getEl = function () {
			return el;
		};
		this.isAnimated = function () {
			return isAnimated;
		};
		this.getStartTime = function () {
			return startTime;
		};
		this.runtimeAttributes = {};
		this.animate = function () {
			if (this.isAnimated()) {
				return false;
			}
			this.currentFrame = 0;
			this.totalFrames = (this.useSeconds) ? Math.ceil(YAHOO.util.AnimMgr.fps * this.duration) : this.duration;
			YAHOO.util.AnimMgr.registerElement(this);
		};
		this.stop = function (finish) {
			if (finish) {
				this.currentFrame = this.totalFrames;
				this._onTween.fire();
			}
			YAHOO.util.AnimMgr.stop(this);
		};
		var onStart = function () {
			this.onStart.fire();
			this.runtimeAttributes = {};
			for (var attr in this.attributes) {
				this.setRuntimeAttribute(attr);
			}
			isAnimated = true;
			actualFrames = 0;
			startTime = new Date();
		};
		var onTween = function () {
			var data = {
				duration: new Date() - this.getStartTime(),
				currentFrame: this.currentFrame
			};
			data.toString = function () {
				return ('duration: ' + data.duration + ', currentFrame: ' + data.currentFrame);
			};
			this.onTween.fire(data);
			var runtimeAttributes = this.runtimeAttributes;
			for (var attr in runtimeAttributes) {
				this.setAttribute(attr, this.doMethod(attr, runtimeAttributes[attr].start, runtimeAttributes[attr].end), runtimeAttributes[attr].unit);
			}
			actualFrames += 1;
		};
		var onComplete = function () {
			var actual_duration = (new Date() - startTime) / 1000;
			var data = {
				duration: actual_duration,
				frames: actualFrames,
				fps: actualFrames / actual_duration
			};
			data.toString = function () {
				return ('duration: ' + data.duration + ', frames: ' + data.frames + ', fps: ' + data.fps);
			};
			isAnimated = false;
			actualFrames = 0;
			this.onComplete.fire(data);
		};
		this._onStart = new YAHOO.util.CustomEvent('_start', this, true);
		this.onStart = new YAHOO.util.CustomEvent('start', this);
		this.onTween = new YAHOO.util.CustomEvent('tween', this);
		this._onTween = new YAHOO.util.CustomEvent('_tween', this, true);
		this.onComplete = new YAHOO.util.CustomEvent('complete', this);
		this._onComplete = new YAHOO.util.CustomEvent('_complete', this, true);
		this._onStart.subscribe(onStart);
		this._onTween.subscribe(onTween);
		this._onComplete.subscribe(onComplete);
	}
};
YAHOO.util.AnimMgr = new
function () {
	var thread = null;
	var queue = [];
	var tweenCount = 0;
	this.fps = 200;
	this.delay = 1;
	this.registerElement = function (tween) {
		queue[queue.length] = tween;
		tweenCount += 1;
		tween._onStart.fire();
		this.start();
	};
	this.unRegister = function (tween, index) {
		tween._onComplete.fire();
		index = index || getIndex(tween);
		if (index != -1) {
			queue.splice(index, 1);
		}
		tweenCount -= 1;
		if (tweenCount <= 0) {
			this.stop();
		}
	};
	this.start = function () {
		if (thread === null) {
			thread = setInterval(this.run, this.delay);
		}
	};
	this.stop = function (tween) {
		if (!tween) {
			clearInterval(thread);
			for (var i = 0, len = queue.length; i < len; ++i) {
				if (queue[i].isAnimated()) {
					this.unRegister(tween, i);
				}
			}
			queue = [];
			thread = null;
			tweenCount = 0;
		} else {
			this.unRegister(tween);
		}
	};
	this.run = function () {
		for (var i = 0, len = queue.length; i < len; ++i) {
			var tween = queue[i];
			if (!tween || !tween.isAnimated()) {
				continue;
			}
			if (tween.currentFrame < tween.totalFrames || tween.totalFrames === null) {
				tween.currentFrame += 1;
				if (tween.useSeconds) {
					correctFrame(tween);
				}
				tween._onTween.fire();
			} else {
				YAHOO.util.AnimMgr.stop(tween, i);
			}
		}
	};
	var getIndex = function (anim) {
		for (var i = 0, len = queue.length; i < len; ++i) {
			if (queue[i] == anim) {
				return i;
			}
		}
		return -1;
	};
	var correctFrame = function (tween) {
		var frames = tween.totalFrames;
		var frame = tween.currentFrame;
		var expected = (tween.currentFrame * tween.duration * 1000 / tween.totalFrames);
		var elapsed = (new Date() - tween.getStartTime());
		var tweak = 0;
		if (elapsed < tween.duration * 1000) {
			tweak = Math.round((elapsed / expected - 1) * tween.currentFrame);
		} else {
			tweak = frames - (frame + 1);
		}
		if (tweak > 0 && isFinite(tweak)) {
			if (tween.currentFrame + tweak >= frames) {
				tweak = frames - (frame + 1);
			}
			tween.currentFrame += tweak;
		}
	};
};
YAHOO.util.Bezier = new
function () {
	this.getPosition = function (points, t) {
		var n = points.length;
		var tmp = [];
		for (var i = 0; i < n; ++i) {
			tmp[i] = [points[i][0], points[i][1]];
		}
		for (var j = 1; j < n; ++j) {
			for (i = 0; i < n - j; ++i) {
				tmp[i][0] = (1 - t) * tmp[i][0] + t * tmp[parseInt(i + 1, 10)][0];
				tmp[i][1] = (1 - t) * tmp[i][1] + t * tmp[parseInt(i + 1, 10)][1];
			}
		}
		return [tmp[0][0], tmp[0][1]];
	};
};
(function () {
	YAHOO.util.ColorAnim = function (el, attributes, duration, method) {
		YAHOO.util.ColorAnim.superclass.constructor.call(this, el, attributes, duration, method);
	};
	YAHOO.extend(YAHOO.util.ColorAnim, YAHOO.util.Anim);
	var Y = YAHOO.util;
	var superclass = Y.ColorAnim.superclass;
	var proto = Y.ColorAnim.prototype;
	proto.toString = function () {
		var el = this.getEl();
		var id = el.id || el.tagName;
		return ("ColorAnim " + id);
	};
	proto.patterns.color = /color$/i;
	proto.patterns.rgb = /^rgb\(([0-9]+)\s*,\s*([0-9]+)\s*,\s*([0-9]+)\)$/i;
	proto.patterns.hex = /^#?([0-9A-F]{2})([0-9A-F]{2})([0-9A-F]{2})$/i;
	proto.patterns.hex3 = /^#?([0-9A-F]{1})([0-9A-F]{1})([0-9A-F]{1})$/i;
	proto.patterns.transparent = /^transparent|rgba\(0, 0, 0, 0\)$/;
	proto.parseColor = function (s) {
		if (s.length == 3) {
			return s;
		}
		var c = this.patterns.hex.exec(s);
		if (c && c.length == 4) {
			return [parseInt(c[1], 16), parseInt(c[2], 16), parseInt(c[3], 16)];
		}
		c = this.patterns.rgb.exec(s);
		if (c && c.length == 4) {
			return [parseInt(c[1], 10), parseInt(c[2], 10), parseInt(c[3], 10)];
		}
		c = this.patterns.hex3.exec(s);
		if (c && c.length == 4) {
			return [parseInt(c[1] + c[1], 16), parseInt(c[2] + c[2], 16), parseInt(c[3] + c[3], 16)];
		}
		return null;
	};
	proto.getAttribute = function (attr) {
		var el = this.getEl();
		if (this.patterns.color.test(attr)) {
			var val = YAHOO.util.Dom.getStyle(el, attr);
			if (this.patterns.transparent.test(val)) {
				var parent = el.parentNode;
				val = Y.Dom.getStyle(parent, attr);
				while (parent && this.patterns.transparent.test(val)) {
					parent = parent.parentNode;
					val = Y.Dom.getStyle(parent, attr);
					if (parent.tagName.toUpperCase() == 'HTML') {
						val = '#fff';
					}
				}
			}
		} else {
			val = superclass.getAttribute.call(this, attr);
		}
		return val;
	};
	proto.doMethod = function (attr, start, end) {
		var val;
		if (this.patterns.color.test(attr)) {
			val = [];
			for (var i = 0, len = start.length; i < len; ++i) {
				val[i] = superclass.doMethod.call(this, attr, start[i], end[i]);
			}
			val = 'rgb(' + Math.floor(val[0]) + ',' + Math.floor(val[1]) + ',' + Math.floor(val[2]) + ')';
		} else {
			val = superclass.doMethod.call(this, attr, start, end);
		}
		return val;
	};
	proto.setRuntimeAttribute = function (attr) {
		superclass.setRuntimeAttribute.call(this, attr);
		if (this.patterns.color.test(attr)) {
			var attributes = this.attributes;
			var start = this.parseColor(this.runtimeAttributes[attr].start);
			var end = this.parseColor(this.runtimeAttributes[attr].end);
			if (typeof attributes[attr]['to'] === 'undefined' && typeof attributes[attr]['by'] !== 'undefined') {
				end = this.parseColor(attributes[attr].by);
				for (var i = 0, len = start.length; i < len; ++i) {
					end[i] = start[i] + end[i];
				}
			}
			this.runtimeAttributes[attr].start = start;
			this.runtimeAttributes[attr].end = end;
		}
	};
})();
YAHOO.util.Easing = {
	easeNone: function (t, b, c, d) {
		return c * t / d + b;
	},
	easeIn: function (t, b, c, d) {
		return c * (t /= d) * t + b;
	},
	easeOut: function (t, b, c, d) {
		return -c * (t /= d) * (t - 2) + b;
	},
	easeBoth: function (t, b, c, d) {
		if ((t /= d / 2) < 1) return c / 2 * t * t + b;
		return -c / 2 * ((--t) * (t - 2) - 1) + b;
	},
	easeInStrong: function (t, b, c, d) {
		return c * (t /= d) * t * t * t + b;
	},
	easeOutStrong: function (t, b, c, d) {
		return -c * ((t = t / d - 1) * t * t * t - 1) + b;
	},
	easeBothStrong: function (t, b, c, d) {
		if ((t /= d / 2) < 1) return c / 2 * t * t * t * t + b;
		return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
	},
	elasticIn: function (t, b, c, d, a, p) {
		if (t == 0) return b;
		if ((t /= d) == 1) return b + c;
		if (!p) p = d * .3;
		if (!a || a < Math.abs(c)) {
			a = c;
			var s = p / 4;
		} else var s = p / (2 * Math.PI) * Math.asin(c / a);
		return -(a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
	},
	elasticOut: function (t, b, c, d, a, p) {
		if (t == 0) return b;
		if ((t /= d) == 1) return b + c;
		if (!p) p = d * .3;
		if (!a || a < Math.abs(c)) {
			a = c;
			var s = p / 4;
		} else var s = p / (2 * Math.PI) * Math.asin(c / a);
		return a * Math.pow(2, -10 * t) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b;
	},
	elasticBoth: function (t, b, c, d, a, p) {
		if (t == 0) return b;
		if ((t /= d / 2) == 2) return b + c;
		if (!p) p = d * (.3 * 1.5);
		if (!a || a < Math.abs(c)) {
			a = c;
			var s = p / 4;
		} else var s = p / (2 * Math.PI) * Math.asin(c / a);
		if (t < 1) return -.5 * (a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
		return a * Math.pow(2, -10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p) * .5 + c + b;
	},
	backIn: function (t, b, c, d, s) {
		if (typeof s == 'undefined') s = 1.70158;
		return c * (t /= d) * t * ((s + 1) * t - s) + b;
	},
	backOut: function (t, b, c, d, s) {
		if (typeof s == 'undefined') s = 1.70158;
		return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
	},
	backBoth: function (t, b, c, d, s) {
		if (typeof s == 'undefined') s = 1.70158;
		if ((t /= d / 2) < 1) return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
		return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2) + b;
	},
	bounceIn: function (t, b, c, d) {
		return c - YAHOO.util.Easing.bounceOut(d - t, 0, c, d) + b;
	},
	bounceOut: function (t, b, c, d) {
		if ((t /= d) < (1 / 2.75)) {
			return c * (7.5625 * t * t) + b;
		} else if (t < (2 / 2.75)) {
			return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
		} else if (t < (2.5 / 2.75)) {
			return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
		} else {
			return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b;
		}
	},
	bounceBoth: function (t, b, c, d) {
		if (t < d / 2) return YAHOO.util.Easing.bounceIn(t * 2, 0, c, d) * .5 + b;
		return YAHOO.util.Easing.bounceOut(t * 2 - d, 0, c, d) * .5 + c * .5 + b;
	}
};
(function () {
	YAHOO.util.Motion = function (el, attributes, duration, method) {
		if (el) {
			YAHOO.util.Motion.superclass.constructor.call(this, el, attributes, duration, method);
		}
	};
	YAHOO.extend(YAHOO.util.Motion, YAHOO.util.ColorAnim);
	var Y = YAHOO.util;
	var superclass = Y.Motion.superclass;
	var proto = Y.Motion.prototype;
	proto.toString = function () {
		var el = this.getEl();
		var id = el.id || el.tagName;
		return ("Motion " + id);
	};
	proto.patterns.points = /^points$/i;
	proto.setAttribute = function (attr, val, unit) {
		if (this.patterns.points.test(attr)) {
			unit = unit || 'px';
			superclass.setAttribute.call(this, 'left', val[0], unit);
			superclass.setAttribute.call(this, 'top', val[1], unit);
		} else {
			superclass.setAttribute.call(this, attr, val, unit);
		}
	};
	proto.getAttribute = function (attr) {
		if (this.patterns.points.test(attr)) {
			var val = [superclass.getAttribute.call(this, 'left'), superclass.getAttribute.call(this, 'top')];
		} else {
			val = superclass.getAttribute.call(this, attr);
		}
		return val;
	};
	proto.doMethod = function (attr, start, end) {
		var val = null;
		if (this.patterns.points.test(attr)) {
			var t = this.method(this.currentFrame, 0, 100, this.totalFrames) / 100;
			val = Y.Bezier.getPosition(this.runtimeAttributes[attr], t);
		} else {
			val = superclass.doMethod.call(this, attr, start, end);
		}
		return val;
	};
	proto.setRuntimeAttribute = function (attr) {
		if (this.patterns.points.test(attr)) {
			var el = this.getEl();
			var attributes = this.attributes;
			var start;
			var control = attributes['points']['control'] || [];
			var end;
			var i, len;
			if (control.length > 0 && !(control[0] instanceof Array)) {
				control = [control];
			} else {
				var tmp = [];
				for (i = 0, len = control.length; i < len; ++i) {
					tmp[i] = control[i];
				}
				control = tmp;
			}
			if (Y.Dom.getStyle(el, 'position') == 'static') {
				Y.Dom.setStyle(el, 'position', 'relative');
			}
			if (isset(attributes['points']['from'])) {
				Y.Dom.setXY(el, attributes['points']['from']);
			} else {
				Y.Dom.setXY(el, Y.Dom.getXY(el));
			}
			start = this.getAttribute('points');
			if (isset(attributes['points']['to'])) {
				end = translateValues.call(this, attributes['points']['to'], start);
				var pageXY = Y.Dom.getXY(this.getEl());
				for (i = 0, len = control.length; i < len; ++i) {
					control[i] = translateValues.call(this, control[i], start);
				}
			} else if (isset(attributes['points']['by'])) {
				end = [start[0] + attributes['points']['by'][0], start[1] + attributes['points']['by'][1]];
				for (i = 0, len = control.length; i < len; ++i) {
					control[i] = [start[0] + control[i][0], start[1] + control[i][1]];
				}
			}
			this.runtimeAttributes[attr] = [start];
			if (control.length > 0) {
				this.runtimeAttributes[attr] = this.runtimeAttributes[attr].concat(control);
			}
			this.runtimeAttributes[attr][this.runtimeAttributes[attr].length] = end;
		} else {
			superclass.setRuntimeAttribute.call(this, attr);
		}
	};
	var translateValues = function (val, start) {
		var pageXY = Y.Dom.getXY(this.getEl());
		val = [val[0] - pageXY[0] + start[0], val[1] - pageXY[1] + start[1]];
		return val;
	};
	var isset = function (prop) {
		return (typeof prop !== 'undefined');
	};
})();
(function () {
	YAHOO.util.Scroll = function (el, attributes, duration, method) {
		if (el) {
			YAHOO.util.Scroll.superclass.constructor.call(this, el, attributes, duration, method);
		}
	};
	YAHOO.extend(YAHOO.util.Scroll, YAHOO.util.ColorAnim);
	var Y = YAHOO.util;
	var superclass = Y.Scroll.superclass;
	var proto = Y.Scroll.prototype;
	proto.toString = function () {
		var el = this.getEl();
		var id = el.id || el.tagName;
		return ("Scroll " + id);
	};
	proto.doMethod = function (attr, start, end) {
		var val = null;
		if (attr == 'scroll') {
			val = [this.method(this.currentFrame, start[0], end[0] - start[0], this.totalFrames), this.method(this.currentFrame, start[1], end[1] - start[1], this.totalFrames)];
		} else {
			val = superclass.doMethod.call(this, attr, start, end);
		}
		return val;
	};
	proto.getAttribute = function (attr) {
		var val = null;
		var el = this.getEl();
		if (attr == 'scroll') {
			val = [el.scrollLeft, el.scrollTop];
		} else {
			val = superclass.getAttribute.call(this, attr);
		}
		return val;
	};
	proto.setAttribute = function (attr, val, unit) {
		var el = this.getEl();
		if (attr == 'scroll') {
			el.scrollLeft = val[0];
			el.scrollTop = val[1];
		} else {
			superclass.setAttribute.call(this, attr, val, unit);
		}
	};
})();
(function () {
	var _1 = YAHOO.util.Event;
	var _2 = YAHOO.util.Dom;
	YAHOO.util.DragDrop = function (id, _4, _5) {
		if (id) {
			this.init(id, _4, _5);
		}
	};
	YAHOO.util.DragDrop.prototype = {
		id: null,
		config: null,
		dragElId: null,
		handleElId: null,
		invalidHandleTypes: null,
		invalidHandleIds: null,
		invalidHandleClasses: null,
		startPageX: 0,
		startPageY: 0,
		groups: null,
		locked: false,
		lock: function () {
			this.locked = true;
		},
		unlock: function () {
			this.locked = false;
		},
		isTarget: true,
		padding: null,
		_domRef: null,
		__ygDragDrop: true,
		constrainX: false,
		constrainY: false,
		minX: 0,
		maxX: 0,
		minY: 0,
		maxY: 0,
		maintainOffset: false,
		xTicks: null,
		yTicks: null,
		primaryButtonOnly: true,
		available: false,
		hasOuterHandles: false,
		b4StartDrag: function (x, y) {},
		startDrag: function (x, y) {},
		b4Drag: function (e) {},
		onDrag: function (e) {},
		onDragEnter: function (e, id) {},
		b4DragOver: function (e) {},
		onDragOver: function (e, id) {},
		b4DragOut: function (e) {},
		onDragOut: function (e, id) {},
		b4DragDrop: function (e) {},
		onDragDrop: function (e, id) {},
		onInvalidDrop: function (e) {},
		b4EndDrag: function (e) {},
		endDrag: function (e) {},
		b4MouseDown: function (e) {},
		onMouseDown: function (e) {},
		onMouseUp: function (e) {},
		onAvailable: function () {},
		getEl: function () {
			if (!this._domRef) {
				this._domRef = _2.get(this.id);
			}
			return this._domRef;
		},
		getDragEl: function () {
			return _2.get(this.dragElId);
		},
		init: function (id, _9, _10) {
			this.initTarget(id, _9, _10);
			_1.on(this.id, "mousedown", this.handleMouseDown, this, true);
		},
		initTarget: function (id, _11, _12) {
			this.config = _12 || {};
			this.DDM = YAHOO.util.DDM;
			this.groups = {};
			if (typeof id !== "string") {
				YAHOO.log("id is not a string, assuming it is an HTMLElement");
				id = _2.generateId(id);
			}
			this.id = id;
			this.addToGroup((_11) ? _11 : "default");
			this.handleElId = id;
			_1.onAvailable(id, this.handleOnAvailable, this, true);
			this.setDragElId(id);
			this.invalidHandleTypes = {
				A: "A"
			};
			this.invalidHandleIds = {};
			this.invalidHandleClasses = [];
			this.applyConfig();
		},
		applyConfig: function () {
			this.padding = this.config.padding || [0, 0, 0, 0];
			this.isTarget = (this.config.isTarget !== false);
			this.maintainOffset = (this.config.maintainOffset);
			this.primaryButtonOnly = (this.config.primaryButtonOnly !== false);
		},
		handleOnAvailable: function () {
			this.available = true;
			this.resetConstraints();
			this.onAvailable();
		},
		setPadding: function (_13, _14, _15, _16) {
			if (!_14 && 0 !== _14) {
				this.padding = [_13, _13, _13, _13];
			} else {
				if (!_15 && 0 !== _15) {
					this.padding = [_13, _14, _13, _14];
				} else {
					this.padding = [_13, _14, _15, _16];
				}
			}
		},
		setInitPosition: function (_17, _18) {
			var el = this.getEl();
			if (!this.DDM.verifyEl(el)) {
				return;
			}
			var dx = _17 || 0;
			var dy = _18 || 0;
			var p = _2.getXY(el);
			this.initPageX = p[0] - dx;
			this.initPageY = p[1] - dy;
			this.lastPageX = p[0];
			this.lastPageY = p[1];
			this.setStartPosition(p);
		},
		setStartPosition: function (pos) {
			var p = pos || _2.getXY(this.getEl());
			this.deltaSetXY = null;
			this.startPageX = p[0];
			this.startPageY = p[1];
		},
		addToGroup: function (_24) {
			this.groups[_24] = true;
			this.DDM.regDragDrop(this, _24);
		},
		removeFromGroup: function (_25) {
			if (this.groups[_25]) {
				delete this.groups[_25];
			}
			this.DDM.removeDDFromGroup(this, _25);
		},
		setDragElId: function (id) {
			this.dragElId = id;
		},
		setHandleElId: function (id) {
			if (typeof id !== "string") {
				YAHOO.log("id is not a string, assuming it is an HTMLElement");
				id = _2.generateId(id);
			}
			this.handleElId = id;
			this.DDM.regHandle(this.id, id);
		},
		setOuterHandleElId: function (id) {
			if (typeof id !== "string") {
				YAHOO.log("id is not a string, assuming it is an HTMLElement");
				id = _2.generateId(id);
			}
			_1.on(id, "mousedown", this.handleMouseDown, this, true);
			this.setHandleElId(id);
			this.hasOuterHandles = true;
		},
		unreg: function () {
			_1.removeListener(this.id, "mousedown", this.handleMouseDown);
			this._domRef = null;
			this.DDM._remove(this);
		},
		isLocked: function () {
			return (this.DDM.isLocked() || this.locked);
		},
		handleMouseDown: function (e, oDD) {
			var _27 = e.which || e.button;
			if (this.primaryButtonOnly && _27 > 1) {
				return;
			}
			if (this.isLocked()) {
				return;
			}
			this.DDM.refreshCache(this.groups);
			var pt = new YAHOO.util.Point(_1.getPageX(e), _1.getPageY(e));
			if (!this.hasOuterHandles && !this.DDM.isOverTarget(pt, this)) {} else {
				if (this.clickValidator(e)) {
					this.setStartPosition();
					this.b4MouseDown(e);
					this.onMouseDown(e);
					this.DDM.handleMouseDown(e, this);
					this.DDM.stopEvent(e);
				} else {}
			}
		},
		clickValidator: function (e) {
			var _29 = _1.getTarget(e);
			return (this.isValidHandleChild(_29) && (this.id == this.handleElId || this.DDM.handleWasClicked(_29, this.id)));
		},
		addInvalidHandleType: function (_30) {
			var _31 = _30.toUpperCase();
			this.invalidHandleTypes[_31] = _31;
		},
		addInvalidHandleId: function (id) {
			if (typeof id !== "string") {
				YAHOO.log("id is not a string, assuming it is an HTMLElement");
				id = _2.generateId(id);
			}
			this.invalidHandleIds[id] = id;
		},
		addInvalidHandleClass: function (_32) {
			this.invalidHandleClasses.push(_32);
		},
		removeInvalidHandleType: function (_33) {
			var _34 = _33.toUpperCase();
			delete this.invalidHandleTypes[_34];
		},
		removeInvalidHandleId: function (id) {
			if (typeof id !== "string") {
				YAHOO.log("id is not a string, assuming it is an HTMLElement");
				id = _2.generateId(id);
			}
			delete this.invalidHandleIds[id];
		},
		removeInvalidHandleClass: function (_35) {
			for (var i = 0, len = this.invalidHandleClasses.length; i < len; ++i) {
				if (this.invalidHandleClasses[i] == _35) {
					delete this.invalidHandleClasses[i];
				}
			}
		},
		isValidHandleChild: function (_37) {
			var _38 = true;
			var _39;
			try {
				_39 = _37.nodeName.toUpperCase();
			} catch (e) {
				_39 = _37.nodeName;
			}
			_38 = _38 && !this.invalidHandleTypes[_39];
			_38 = _38 && !this.invalidHandleIds[_37.id];
			for (var i = 0, len = this.invalidHandleClasses.length; _38 && i < len; ++i) {
				_38 = !_2.hasClass(_37, this.invalidHandleClasses[i]);
			}
			return _38;
		},
		setXTicks: function (_40, _41) {
			this.xTicks = [];
			this.xTickSize = _41;
			var _42 = {};
			for (var i = this.initPageX; i >= this.minX; i = i - _41) {
				if (!_42[i]) {
					this.xTicks[this.xTicks.length] = i;
					_42[i] = true;
				}
			}
			for (i = this.initPageX; i <= this.maxX; i = i + _41) {
				if (!_42[i]) {
					this.xTicks[this.xTicks.length] = i;
					_42[i] = true;
				}
			}
			this.xTicks.sort(this.DDM.numericSort);
		},
		setYTicks: function (_43, _44) {
			this.yTicks = [];
			this.yTickSize = _44;
			var _45 = {};
			for (var i = this.initPageY; i >= this.minY; i = i - _44) {
				if (!_45[i]) {
					this.yTicks[this.yTicks.length] = i;
					_45[i] = true;
				}
			}
			for (i = this.initPageY; i <= this.maxY; i = i + _44) {
				if (!_45[i]) {
					this.yTicks[this.yTicks.length] = i;
					_45[i] = true;
				}
			}
			this.yTicks.sort(this.DDM.numericSort);
		},
		setXConstraint: function (_46, _47, _48) {
			this.leftConstraint = _46;
			this.rightConstraint = _47;
			this.minX = this.initPageX - _46;
			this.maxX = this.initPageX + _47;
			if (_48) {
				this.setXTicks(this.initPageX, _48);
			}
			this.constrainX = true;
		},
		clearConstraints: function () {
			this.constrainX = false;
			this.constrainY = false;
			this.clearTicks();
		},
		clearTicks: function () {
			this.xTicks = null;
			this.yTicks = null;
			this.xTickSize = 0;
			this.yTickSize = 0;
		},
		setYConstraint: function (iUp, _50, _51) {
			this.topConstraint = iUp;
			this.bottomConstraint = _50;
			this.minY = this.initPageY - iUp;
			this.maxY = this.initPageY + _50;
			if (_51) {
				this.setYTicks(this.initPageY, _51);
			}
			this.constrainY = true;
		},
		resetConstraints: function () {
			if (this.initPageX || this.initPageX === 0) {
				var dx = (this.maintainOffset) ? this.lastPageX - this.initPageX : 0;
				var dy = (this.maintainOffset) ? this.lastPageY - this.initPageY : 0;
				this.setInitPosition(dx, dy);
			} else {
				this.setInitPosition();
			}
			if (this.constrainX) {
				this.setXConstraint(this.leftConstraint, this.rightConstraint, this.xTickSize);
			}
			if (this.constrainY) {
				this.setYConstraint(this.topConstraint, this.bottomConstraint, this.yTickSize);
			}
		},
		getTick: function (val, _53) {
			if (!_53) {
				return val;
			} else {
				if (_53[0] >= val) {
					return _53[0];
				} else {
					for (var i = 0, len = _53.length; i < len; ++i) {
						var _54 = i + 1;
						if (_53[_54] && _53[_54] >= val) {
							var _55 = val - _53[i];
							var _56 = _53[_54] - val;
							return (_56 > _55) ? _53[i] : _53[_54];
						}
					}
					return _53[_53.length - 1];
				}
			}
		},
		toString: function () {
			return ("DragDrop " + this.id);
		}
	};
})();
if (!YAHOO.util.DragDropMgr) {
	YAHOO.util.DragDropMgr = function () {
		var _57 = YAHOO.util.Event;
		return {
			ids: {},
			handleIds: {},
			dragCurrent: null,
			dragOvers: {},
			deltaX: 0,
			deltaY: 0,
			preventDefault: true,
			stopPropagation: true,
			initalized: false,
			locked: false,
			init: function () {
				this.initialized = true;
			},
			POINT: 0,
			INTERSECT: 1,
			mode: 0,
			_execOnAll: function (_58, _59) {
				for (var i in this.ids) {
					for (var j in this.ids[i]) {
						var oDD = this.ids[i][j];
						if (!this.isTypeOfDD(oDD)) {
							continue;
						}
						oDD[_58].apply(oDD, _59);
					}
				}
			},
			_onLoad: function () {
				this.init();
				_57.on(document, "mouseup", this.handleMouseUp, this, true);
				_57.on(document, "mousemove", this.handleMouseMove, this, true);
				_57.on(window, "unload", this._onUnload, this, true);
				_57.on(window, "resize", this._onResize, this, true);
			},
			_onResize: function (e) {
				this._execOnAll("resetConstraints", []);
			},
			lock: function () {
				this.locked = true;
			},
			unlock: function () {
				this.locked = false;
			},
			isLocked: function () {
				return this.locked;
			},
			locationCache: {},
			useCache: true,
			clickPixelThresh: 3,
			clickTimeThresh: 1000,
			dragThreshMet: false,
			clickTimeout: null,
			startX: 0,
			startY: 0,
			regDragDrop: function (oDD, _61) {
				if (!this.initialized) {
					this.init();
				}
				if (!this.ids[_61]) {
					this.ids[_61] = {};
				}
				this.ids[_61][oDD.id] = oDD;
			},
			removeDDFromGroup: function (oDD, _62) {
				if (!this.ids[_62]) {
					this.ids[_62] = {};
				}
				var obj = this.ids[_62];
				if (obj && obj[oDD.id]) {
					delete obj[oDD.id];
				}
			},
			_remove: function (oDD) {
				for (var g in oDD.groups) {
					if (g && this.ids[g][oDD.id]) {
						delete this.ids[g][oDD.id];
					}
				}
				delete this.handleIds[oDD.id];
			},
			regHandle: function (_65, _66) {
				if (!this.handleIds[_65]) {
					this.handleIds[_65] = {};
				}
				this.handleIds[_65][_66] = _66;
			},
			isDragDrop: function (id) {
				return (this.getDDById(id)) ? true : false;
			},
			getRelated: function (_67, _68) {
				var _69 = [];
				for (var i in _67.groups) {
					for (j in this.ids[i]) {
						var dd = this.ids[i][j];
						if (!this.isTypeOfDD(dd)) {
							continue;
						}
						if (!_68 || dd.isTarget) {
							_69[_69.length] = dd;
						}
					}
				}
				return _69;
			},
			isLegalTarget: function (oDD, _71) {
				var _72 = this.getRelated(oDD, true);
				for (var i = 0, len = _72.length; i < len; ++i) {
					if (_72[i].id == _71.id) {
						return true;
					}
				}
				return false;
			},
			isTypeOfDD: function (oDD) {
				return (oDD && oDD.__ygDragDrop);
			},
			isHandle: function (_73, _74) {
				return (this.handleIds[_73] && this.handleIds[_73][_74]);
			},
			getDDById: function (id) {
				for (var i in this.ids) {
					if (this.ids[i][id]) {
						return this.ids[i][id];
					}
				}
				return null;
			},
			handleMouseDown: function (e, oDD) {
				this.currentTarget = YAHOO.util.Event.getTarget(e);
				this.dragCurrent = oDD;
				var el = oDD.getEl();
				this.startX = YAHOO.util.Event.getPageX(e);
				this.startY = YAHOO.util.Event.getPageY(e);
				this.deltaX = this.startX - el.offsetLeft;
				this.deltaY = this.startY - el.offsetTop;
				this.dragThreshMet = false;
				this.clickTimeout = setTimeout(function () {
					var DDM = YAHOO.util.DDM;
					DDM.startDrag(DDM.startX, DDM.startY);
				}, this.clickTimeThresh);
			},
			startDrag: function (x, y) {
				clearTimeout(this.clickTimeout);
				if (this.dragCurrent) {
					this.dragCurrent.b4StartDrag(x, y);
					this.dragCurrent.startDrag(x, y);
				}
				this.dragThreshMet = true;
			},
			handleMouseUp: function (e) {
				if (!this.dragCurrent) {
					return;
				}
				clearTimeout(this.clickTimeout);
				if (this.dragThreshMet) {
					this.fireEvents(e, true);
				} else {}
				this.stopDrag(e);
				this.stopEvent(e);
			},
			stopEvent: function (e) {
				if (this.stopPropagation) {
					YAHOO.util.Event.stopPropagation(e);
				}
				if (this.preventDefault) {
					YAHOO.util.Event.preventDefault(e);
				}
			},
			stopDrag: function (e) {
				if (this.dragCurrent) {
					if (this.dragThreshMet) {
						this.dragCurrent.b4EndDrag(e);
						this.dragCurrent.endDrag(e);
					}
					this.dragCurrent.onMouseUp(e);
				}
				this.dragCurrent = null;
				this.dragOvers = {};
			},
			handleMouseMove: function (e) {
				if (!this.dragCurrent) {
					return true;
				}
				if (YAHOO.util.Event.isIE && !e.button) {
					this.stopEvent(e);
					return this.handleMouseUp(e);
				}
				if (!this.dragThreshMet) {
					var _76 = Math.abs(this.startX - YAHOO.util.Event.getPageX(e));
					var _77 = Math.abs(this.startY - YAHOO.util.Event.getPageY(e));
					if (_76 > this.clickPixelThresh || _77 > this.clickPixelThresh) {
						this.startDrag(this.startX, this.startY);
					}
				}
				if (this.dragThreshMet) {
					this.dragCurrent.b4Drag(e);
					this.dragCurrent.onDrag(e);
					this.fireEvents(e, false);
				}
				this.stopEvent(e);
				return true;
			},
			fireEvents: function (e, _78) {
				var dc = this.dragCurrent;
				if (!dc || dc.isLocked()) {
					return;
				}
				var x = YAHOO.util.Event.getPageX(e);
				var y = YAHOO.util.Event.getPageY(e);
				var pt = new YAHOO.util.Point(x, y);
				var _80 = [];
				var _81 = [];
				var _82 = [];
				var _83 = [];
				var _84 = [];
				for (var i in this.dragOvers) {
					var ddo = this.dragOvers[i];
					if (!this.isTypeOfDD(ddo)) {
						continue;
					}
					if (!this.isOverTarget(pt, ddo, this.mode)) {
						_81.push(ddo);
					}
					_80[i] = true;
					delete this.dragOvers[i];
				}
				for (var _86 in dc.groups) {
					if ("string" != typeof _86) {
						continue;
					}
					for (i in this.ids[_86]) {
						var oDD = this.ids[_86][i];
						if (!this.isTypeOfDD(oDD)) {
							continue;
						}
						if (oDD.isTarget && !oDD.isLocked() && oDD != dc) {
							if (this.isOverTarget(pt, oDD, this.mode)) {
								if (_78) {
									_83.push(oDD);
								} else {
									if (!_80[oDD.id]) {
										_84.push(oDD);
									} else {
										_82.push(oDD);
									}
									this.dragOvers[oDD.id] = oDD;
								}
							}
						}
					}
				}
				if (this.mode) {
					if (_81.length) {
						dc.b4DragOut(e, _81);
						dc.onDragOut(e, _81);
					}
					if (_84.length) {
						dc.onDragEnter(e, _84);
					}
					if (_82.length) {
						dc.b4DragOver(e, _82);
						dc.onDragOver(e, _82);
					}
					if (_83.length) {
						dc.b4DragDrop(e, _83);
						dc.onDragDrop(e, _83);
					}
				} else {
					var len = 0;
					for (i = 0, len = _81.length; i < len; ++i) {
						dc.b4DragOut(e, _81[i].id);
						dc.onDragOut(e, _81[i].id);
					}
					for (i = 0, len = _84.length; i < len; ++i) {
						dc.onDragEnter(e, _84[i].id);
					}
					for (i = 0, len = _82.length; i < len; ++i) {
						dc.b4DragOver(e, _82[i].id);
						dc.onDragOver(e, _82[i].id);
					}
					for (i = 0, len = _83.length; i < len; ++i) {
						dc.b4DragDrop(e, _83[i].id);
						dc.onDragDrop(e, _83[i].id);
					}
				}
				if (_78 && !_83.length) {
					dc.onInvalidDrop(e);
				}
			},
			getBestMatch: function (dds) {
				var _89 = null;
				var len = dds.length;
				if (len == 1) {
					_89 = dds[0];
				} else {
					for (var i = 0; i < len; ++i) {
						var dd = dds[i];
						if (dd.cursorIsOver) {
							_89 = dd;
							break;
						} else {
							if (!_89 || _89.overlap.getArea() < dd.overlap.getArea()) {
								_89 = dd;
							}
						}
					}
				}
				return _89;
			},
			refreshCache: function (_90) {
				for (var _91 in _90) {
					if ("string" != typeof _91) {
						continue;
					}
					for (var i in this.ids[_91]) {
						var oDD = this.ids[_91][i];
						if (this.isTypeOfDD(oDD)) {
							var loc = this.getLocation(oDD);
							if (loc) {
								this.locationCache[oDD.id] = loc;
							} else {
								delete this.locationCache[oDD.id];
							}
						}
					}
				}
			},
			verifyEl: function (el) {
				try {
					if (el) {
						var _93 = el.offsetParent;
						if (_93) {
							return true;
						}
					}
				} catch (e) {}
				return false;
			},
			getLocation: function (oDD) {
				if (!this.isTypeOfDD(oDD)) {
					return null;
				}
				var el = oDD.getEl(),
					pos, x1, x2, y1, y2, t, r, b, l;
				try {
					pos = YAHOO.util.Dom.getXY(el);
				} catch (e) {}
				if (!pos) {
					return null;
				}
				x1 = pos[0];
				x2 = x1 + el.offsetWidth;
				y1 = pos[1];
				y2 = y1 + el.offsetHeight;
				t = y1 - oDD.padding[0];
				r = x2 + oDD.padding[1];
				b = y2 + oDD.padding[2];
				l = x1 - oDD.padding[3];
				return new YAHOO.util.Region(t, r, b, l);
			},
			isOverTarget: function (pt, _94, _95) {
				var loc = this.locationCache[_94.id];
				if (!loc || !this.useCache) {
					loc = this.getLocation(_94);
					this.locationCache[_94.id] = loc;
				}
				if (!loc) {
					return false;
				}
				_94.cursorIsOver = loc.contains(pt);
				var dc = this.dragCurrent;
				if (!dc || !dc.getTargetCoord || (!_95 && !dc.constrainX && !dc.constrainY)) {
					return _94.cursorIsOver;
				}
				_94.overlap = null;
				var pos = dc.getTargetCoord(pt.x, pt.y);
				var el = dc.getDragEl();
				var _96 = new YAHOO.util.Region(pos.y, pos.x + el.offsetWidth, pos.y + el.offsetHeight, pos.x);
				var _97 = _96.intersect(loc);
				if (_97) {
					_94.overlap = _97;
					return (_95) ? true : _94.cursorIsOver;
				} else {
					return false;
				}
			},
			_onUnload: function (e, me) {
				this.unregAll();
			},
			unregAll: function () {
				if (this.dragCurrent) {
					this.stopDrag();
					this.dragCurrent = null;
				}
				this._execOnAll("unreg", []);
				for (i in this.elementCache) {
					delete this.elementCache[i];
				}
				this.elementCache = {};
				this.ids = {};
			},
			elementCache: {},
			getElWrapper: function (id) {
				var _99 = this.elementCache[id];
				if (!_99 || !_99.el) {
					_99 = this.elementCache[id] = new this.ElementWrapper(YAHOO.util.Dom.get(id));
				}
				return _99;
			},
			getElement: function (id) {
				return YAHOO.util.Dom.get(id);
			},
			getCss: function (id) {
				var el = YAHOO.util.Dom.get(id);
				return (el) ? el.style : null;
			},
			ElementWrapper: function (el) {
				this.el = el || null;
				this.id = this.el && el.id;
				this.css = this.el && el.style;
			},
			getPosX: function (el) {
				return YAHOO.util.Dom.getX(el);
			},
			getPosY: function (el) {
				return YAHOO.util.Dom.getY(el);
			},
			swapNode: function (n1, n2) {
				if (n1.swapNode) {
					n1.swapNode(n2);
				} else {
					var p = n2.parentNode;
					var s = n2.nextSibling;
					if (s == n1) {
						p.insertBefore(n1, n2);
					} else {
						if (n2 == n1.nextSibling) {
							p.insertBefore(n2, n1);
						} else {
							n1.parentNode.replaceChild(n2, n1);
							p.insertBefore(n1, s);
						}
					}
				}
			},
			getScroll: function () {
				var t, l, dde = document.documentElement,
					db = document.body;
				if (dde && (dde.scrollTop || dde.scrollLeft)) {
					t = dde.scrollTop;
					l = dde.scrollLeft;
				} else {
					if (db) {
						t = db.scrollTop;
						l = db.scrollLeft;
					} else {
						YAHOO.log("could not get scroll property");
					}
				}
				return {
					top: t,
					left: l
				};
			},
			getStyle: function (el, _104) {
				return YAHOO.util.Dom.getStyle(el, _104);
			},
			getScrollTop: function () {
				return this.getScroll().top;
			},
			getScrollLeft: function () {
				return this.getScroll().left;
			},
			moveToEl: function (_105, _106) {
				var _107 = YAHOO.util.Dom.getXY(_106);
				YAHOO.util.Dom.setXY(_105, _107);
			},
			getClientHeight: function () {
				return YAHOO.util.Dom.getViewportHeight();
			},
			getClientWidth: function () {
				return YAHOO.util.Dom.getViewportWidth();
			},
			numericSort: function (a, b) {
				return (a - b);
			},
			_timeoutCount: 0,
			_addListeners: function () {
				var DDM = YAHOO.util.DDM;
				if (YAHOO.util.Event && document) {
					DDM._onLoad();
				} else {
					if (DDM._timeoutCount > 2000) {} else {
						setTimeout(DDM._addListeners, 10);
						if (document && document.body) {
							DDM._timeoutCount += 1;
						}
					}
				}
			},
			handleWasClicked: function (node, id) {
				if (this.isHandle(id, node.id)) {
					return true;
				} else {
					var p = node.parentNode;
					while (p) {
						if (this.isHandle(id, p.id)) {
							return true;
						} else {
							p = p.parentNode;
						}
					}
				}
				return false;
			}
		};
	}();
	YAHOO.util.DDM = YAHOO.util.DragDropMgr;
	YAHOO.util.DDM._addListeners();
}
YAHOO.util.DD = function (id, _111, _112) {
	if (id) {
		this.init(id, _111, _112);
	}
};
YAHOO.extend(YAHOO.util.DD, YAHOO.util.DragDrop, {
	scroll: true,
	autoOffset: function (_113, _114) {
		var x = _113 - this.startPageX;
		var y = _114 - this.startPageY;
		this.setDelta(x, y);
	},
	setDelta: function (_115, _116) {
		this.deltaX = _115;
		this.deltaY = _116;
	},
	setDragElPos: function (_117, _118) {
		var el = this.getDragEl();
		this.alignElWithMouse(el, _117, _118);
	},
	alignElWithMouse: function (el, _119, _120) {
		var _121 = this.getTargetCoord(_119, _120);
		if (!this.deltaSetXY) {
			var _122 = [_121.x, _121.y];
			YAHOO.util.Dom.setXY(el, _122);
			var _123 = parseInt(YAHOO.util.Dom.getStyle(el, "left"), 10);
			var _124 = parseInt(YAHOO.util.Dom.getStyle(el, "top"), 10);
			this.deltaSetXY = [_123 - _121.x, _124 - _121.y];
		} else {
			YAHOO.util.Dom.setStyle(el, "left", (_121.x + this.deltaSetXY[0]) + "px");
			YAHOO.util.Dom.setStyle(el, "top", (_121.y + this.deltaSetXY[1]) + "px");
		}
		this.cachePosition(_121.x, _121.y);
		this.autoScroll(_121.x, _121.y, el.offsetHeight, el.offsetWidth);
	},
	cachePosition: function (_125, _126) {
		if (_125) {
			this.lastPageX = _125;
			this.lastPageY = _126;
		} else {
			var _127 = YAHOO.util.Dom.getXY(this.getEl());
			this.lastPageX = _127[0];
			this.lastPageY = _127[1];
		}
	},
	autoScroll: function (x, y, h, w) {
		if (this.scroll) {
			var _130 = this.DDM.getClientHeight();
			var _131 = this.DDM.getClientWidth();
			var st = this.DDM.getScrollTop();
			var sl = this.DDM.getScrollLeft();
			var bot = h + y;
			var _135 = w + x;
			var _136 = (_130 + st - y - this.deltaY);
			var _137 = (_131 + sl - x - this.deltaX);
			var _138 = 40;
			var _139 = (document.all) ? 80 : 30;
			if (bot > _130 && _136 < _138) {
				window.scrollTo(sl, st + _139);
			}
			if (y < st && st > 0 && y - st < _138) {
				window.scrollTo(sl, st - _139);
			}
			if (_135 > _131 && _137 < _138) {
				window.scrollTo(sl + _139, st);
			}
			if (x < sl && sl > 0 && x - sl < _138) {
				window.scrollTo(sl - _139, st);
			}
		}
	},
	getTargetCoord: function (_140, _141) {
		var x = _140 - this.deltaX;
		var y = _141 - this.deltaY;
		if (this.constrainX) {
			if (x < this.minX) {
				x = this.minX;
			}
			if (x > this.maxX) {
				x = this.maxX;
			}
		}
		if (this.constrainY) {
			if (y < this.minY) {
				y = this.minY;
			}
			if (y > this.maxY) {
				y = this.maxY;
			}
		}
		x = this.getTick(x, this.xTicks);
		y = this.getTick(y, this.yTicks);
		return {
			x: x,
			y: y
		};
	},
	applyConfig: function () {
		YAHOO.util.DD.superclass.applyConfig.call(this);
		this.scroll = (this.config.scroll !== false);
	},
	b4MouseDown: function (e) {
		this.autoOffset(YAHOO.util.Event.getPageX(e), YAHOO.util.Event.getPageY(e));
	},
	b4Drag: function (e) {
		this.setDragElPos(YAHOO.util.Event.getPageX(e), YAHOO.util.Event.getPageY(e));
	},
	toString: function () {
		return ("DD " + this.id);
	}
});
YAHOO.util.DDProxy = function (id, _142, _143) {
	if (id) {
		this.init(id, _142, _143);
		this.initFrame();
	}
};
YAHOO.util.DDProxy.dragElId = "ygddfdiv";
YAHOO.extend(YAHOO.util.DDProxy, YAHOO.util.DD, {
	resizeFrame: true,
	centerFrame: false,
	createFrame: function () {
		var self = this;
		var body = document.body;
		if (!body || !body.firstChild) {
			setTimeout(function () {
				self.createFrame();
			}, 50);
			return;
		}
		var div = this.getDragEl();
		if (!div) {
			div = document.createElement("div");
			div.id = this.dragElId;
			var s = div.style;
			s.position = "absolute";
			s.visibility = "hidden";
			s.cursor = "move";
			s.border = "2px solid #aaa";
			s.zIndex = 999;
			body.insertBefore(div, body.firstChild);
		}
	},
	initFrame: function () {
		this.createFrame();
	},
	applyConfig: function () {
		YAHOO.util.DDProxy.superclass.applyConfig.call(this);
		this.resizeFrame = (this.config.resizeFrame !== false);
		this.centerFrame = (this.config.centerFrame);
		this.setDragElId(this.config.dragElId || YAHOO.util.DDProxy.dragElId);
	},
	showFrame: function (_147, _148) {
		var el = this.getEl();
		var _149 = this.getDragEl();
		var s = _149.style;
		this._resizeProxy();
		if (this.centerFrame) {
			this.setDelta(Math.round(parseInt(s.width, 10) / 2), Math.round(parseInt(s.height, 10) / 2));
		}
		this.setDragElPos(_147, _148);
		YAHOO.util.Dom.setStyle(_149, "visibility", "visible");
	},
	_resizeProxy: function () {
		if (this.resizeFrame) {
			var DOM = YAHOO.util.Dom;
			var el = this.getEl();
			var _151 = this.getDragEl();
			var bt = parseInt(DOM.getStyle(_151, "borderTopWidth"), 10);
			var br = parseInt(DOM.getStyle(_151, "borderRightWidth"), 10);
			var bb = parseInt(DOM.getStyle(_151, "borderBottomWidth"), 10);
			var bl = parseInt(DOM.getStyle(_151, "borderLeftWidth"), 10);
			if (isNaN(bt)) {
				bt = 0;
			}
			if (isNaN(br)) {
				br = 0;
			}
			if (isNaN(bb)) {
				bb = 0;
			}
			if (isNaN(bl)) {
				bl = 0;
			}
			var _156 = Math.max(0, el.offsetWidth - br - bl);
			var _157 = Math.max(0, el.offsetHeight - bt - bb);
			DOM.setStyle(_151, "width", _156 + "px");
			DOM.setStyle(_151, "height", _157 + "px");
		}
	},
	b4MouseDown: function (e) {
		var x = YAHOO.util.Event.getPageX(e);
		var y = YAHOO.util.Event.getPageY(e);
		this.autoOffset(x, y);
		this.setDragElPos(x, y);
	},
	b4StartDrag: function (x, y) {
		this.showFrame(x, y);
	},
	b4EndDrag: function (e) {
		YAHOO.util.Dom.setStyle(this.getDragEl(), "visibility", "hidden");
	},
	endDrag: function (e) {
		var DOM = YAHOO.util.Dom;
		var lel = this.getEl();
		var del = this.getDragEl();
		DOM.setStyle(del, "visibility", "");
		DOM.setStyle(lel, "visibility", "hidden");
		YAHOO.util.DDM.moveToEl(lel, del);
		DOM.setStyle(del, "visibility", "hidden");
		DOM.setStyle(lel, "visibility", "");
	},
	toString: function () {
		return ("DDProxy " + this.id);
	}
});
YAHOO.util.DDTarget = function (id, _160, _161) {
	if (id) {
		this.initTarget(id, _160, _161);
	}
};
YAHOO.extend(YAHOO.util.DDTarget, YAHOO.util.DragDrop, {
	toString: function () {
		return ("DDTarget " + this.id);
	}
});
YAHOO.util.Connect = {
	_msxml_progid: ['MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'],
	_http_header: {},
	_has_http_headers: false,
	_use_default_post_header: true,
	_default_post_header: 'application/x-www-form-urlencoded',
	_isFormSubmit: false,
	_isFileUpload: false,
	_formNode: null,
	_sFormData: null,
	_poll: {},
	_timeOut: {},
	_polling_interval: 50,
	_transaction_id: 0,
	setProgId: function (id) {
		this._msxml_progid.unshift(id);
	},
	setDefaultPostHeader: function (b) {
		this._use_default_post_header = b;
	},
	setPollingInterval: function (i) {
		if (typeof i == 'number' && isFinite(i)) {
			this._polling_interval = i;
		}
	},
	createXhrObject: function (transactionId) {
		var obj, http;
		try {
			http = new XMLHttpRequest();
			obj = {
				conn: http,
				tId: transactionId
			};
		}
		catch (e) {
			for (var i = 0; i < this._msxml_progid.length; ++i) {
				try {
					http = new ActiveXObject(this._msxml_progid[i]);
					obj = {
						conn: http,
						tId: transactionId
					};
					break;
				}
				catch (e) {}
			}
		}
		finally {
			return obj;
		}
	},
	getConnectionObject: function () {
		var o;
		var tId = this._transaction_id;
		try {
			o = this.createXhrObject(tId);
			if (o) {
				this._transaction_id++;
			}
		}
		catch (e) {}
		finally {
			return o;
		}
	},
	asyncRequest: function (method, uri, callback, postData) {
		var o = this.getConnectionObject();
		if (!o) {
			return null;
		}
		else {
			if (this._isFormSubmit) {
				if (this._isFileUpload) {
					this.uploadFile(o.tId, callback, uri, postData);
					this.releaseObject(o);
					return;
				}
				if (method == 'GET') {
					if (this._sFormData.length != 0) {
						uri += ((uri.indexOf('?') == -1) ? '?' : '&') + this._sFormData;
					}
					else {
						uri += "?" + this._sFormData;
					}
				}
				else if (method == 'POST') {
					postData = postData ? this._sFormData + "&" + postData : this._sFormData;
				}
			}
			o.conn.open(method, uri, true);
			if (this._isFormSubmit || (postData && this._use_default_post_header)) {
				this.initHeader('Content-Type', this._default_post_header);
				if (this._isFormSubmit) {
					this.resetFormState();
				}
			}
			if (this._has_http_headers) {
				this.setHeader(o);
			}
			this.handleReadyState(o, callback);
			o.conn.send(postData || null);
			return o;
		}
	},
	handleReadyState: function (o, callback) {
		var oConn = this;
		if (callback && callback.timeout) {
			this._timeOut[o.tId] = window.setTimeout(function () {
				oConn.abort(o, callback, true);
			}, callback.timeout);
		}
		this._poll[o.tId] = window.setInterval(function () {
			if (o.conn && o.conn.readyState == 4) {
				window.clearInterval(oConn._poll[o.tId]);
				delete oConn._poll[o.tId];
				if (callback && callback.timeout) {
					delete oConn._timeOut[o.tId];
				}
				oConn.handleTransactionResponse(o, callback);
			}
		}, this._polling_interval);
	},
	handleTransactionResponse: function (o, callback, isAbort) {
		if (!callback) {
			this.releaseObject(o);
			return;
		}
		var httpStatus, responseObject;
		try {
			if (o.conn.status !== undefined && o.conn.status != 0) {
				httpStatus = o.conn.status;
			}
			else {
				httpStatus = 13030;
			}
		}
		catch (e) {
			httpStatus = 13030;
		}
		if (httpStatus >= 200 && httpStatus < 300) {
			try {
				responseObject = this.createResponseObject(o, callback.argument);
				if (callback.success) {
					if (!callback.scope) {
						callback.success(responseObject);
					}
					else {
						callback.success.apply(callback.scope, [responseObject]);
					}
				}
			}
			catch (e) {}
		}
		else {
			try {
				switch (httpStatus) {
				case 12002:
				case 12029:
				case 12030:
				case 12031:
				case 12152:
				case 13030:
					responseObject = this.createExceptionObject(o.tId, callback.argument, (isAbort ? isAbort : false));
					if (callback.failure) {
						if (!callback.scope) {
							callback.failure(responseObject);
						}
						else {
							callback.failure.apply(callback.scope, [responseObject]);
						}
					}
					break;
				default:
					responseObject = this.createResponseObject(o, callback.argument);
					if (callback.failure) {
						if (!callback.scope) {
							callback.failure(responseObject);
						}
						else {
							callback.failure.apply(callback.scope, [responseObject]);
						}
					}
				}
			}
			catch (e) {}
		}
		this.releaseObject(o);
		responseObject = null;
	},
	createResponseObject: function (o, callbackArg) {
		var obj = {};
		var headerObj = {};
		try {
			var headerStr = o.conn.getAllResponseHeaders();
			var header = headerStr.split('\n');
			for (var i = 0; i < header.length; i++) {
				var delimitPos = header[i].indexOf(':');
				if (delimitPos != -1) {
					headerObj[header[i].substring(0, delimitPos)] = header[i].substring(delimitPos + 2);
				}
			}
		}
		catch (e) {}
		obj.tId = o.tId;
		obj.status = o.conn.status;
		obj.statusText = o.conn.statusText;
		obj.getResponseHeader = headerObj;
		obj.getAllResponseHeaders = headerStr;
		obj.responseText = o.conn.responseText;
		obj.responseXML = o.conn.responseXML;
		if (typeof callbackArg !== undefined) {
			obj.argument = callbackArg;
		}
		return obj;
	},
	createExceptionObject: function (tId, callbackArg, isAbort) {
		var COMM_CODE = 0;
		var COMM_ERROR = 'communication failure';
		var ABORT_CODE = -1;
		var ABORT_ERROR = 'transaction aborted';
		var obj = {};
		obj.tId = tId;
		if (isAbort) {
			obj.status = ABORT_CODE;
			obj.statusText = ABORT_ERROR;
		}
		else {
			obj.status = COMM_CODE;
			obj.statusText = COMM_ERROR;
		}
		if (callbackArg) {
			obj.argument = callbackArg;
		}
		return obj;
	},
	initHeader: function (label, value) {
		if (this._http_header[label] === undefined) {
			this._http_header[label] = value;
		}
		else {
			this._http_header[label] = value + "," + this._http_header[label];
		}
		this._has_http_headers = true;
	},
	setHeader: function (o) {
		for (var prop in this._http_header) {
			if (this._http_header.hasOwnProperty(prop)) {
				o.conn.setRequestHeader(prop, this._http_header[prop]);
			}
		}
		delete this._http_header;
		this._http_header = {};
		this._has_http_headers = false;
	},
	setForm: function (formId, isUpload, secureUri) {
		this.resetFormState();
		var oForm;
		if (typeof formId == 'string') {
			oForm = (document.getElementById(formId) || document.forms[formId]);
		}
		else if (typeof formId == 'object') {
			oForm = formId;
		}
		else {
			return;
		}
		if (isUpload) {
			this.createFrame(secureUri ? secureUri : null);
			this._isFormSubmit = true;
			this._isFileUpload = true;
			this._formNode = oForm;
			return;
		}
		var oElement, oName, oValue, oDisabled;
		var hasSubmit = false;
		for (var i = 0; i < oForm.elements.length; i++) {
			oElement = oForm.elements[i];
			oDisabled = oForm.elements[i].disabled;
			oName = oForm.elements[i].name;
			oValue = oForm.elements[i].value;
			if (!oDisabled && oName) {
				switch (oElement.type) {
				case 'select-one':
				case 'select-multiple':
					for (var j = 0; j < oElement.options.length; j++) {
						if (oElement.options[j].selected) {
							if (window.ActiveXObject) {
								this._sFormData += encodeURIComponent(oName) + '=' + encodeURIComponent(oElement.options[j].attributes['value'].specified ? oElement.options[j].value : oElement.options[j].text) + '&';
							}
							else {
								this._sFormData += encodeURIComponent(oName) + '=' + encodeURIComponent(oElement.options[j].hasAttribute('value') ? oElement.options[j].value : oElement.options[j].text) + '&';
							}
						}
					}
					break;
				case 'radio':
				case 'checkbox':
					if (oElement.checked) {
						this._sFormData += encodeURIComponent(oName) + '=' + encodeURIComponent(oValue) + '&';
					}
					break;
				case 'file':
				case undefined:
				case 'reset':
				case 'button':
					break;
				case 'submit':
					if (hasSubmit == false) {
						this._sFormData += encodeURIComponent(oName) + '=' + encodeURIComponent(oValue) + '&';
						hasSubmit = true;
					}
					break;
				default:
					this._sFormData += encodeURIComponent(oName) + '=' + encodeURIComponent(oValue) + '&';
					break;
				}
			}
		}
		this._isFormSubmit = true;
		this._sFormData = this._sFormData.substr(0, this._sFormData.length - 1);
		return this._sFormData;
	},
	resetFormState: function () {
		this._isFormSubmit = false;
		this._isFileUpload = false;
		this._formNode = null;
		this._sFormData = "";
	},
	createFrame: function (secureUri) {
		var frameId = 'yuiIO' + this._transaction_id;
		if (window.ActiveXObject) {
			var io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
			if (typeof secureUri == 'boolean') {
				io.src = 'javascript:false';
			}
			else if (typeof secureURI == 'string') {
				io.src = secureUri;
			}
		}
		else {
			var io = document.createElement('iframe');
			io.id = frameId;
			io.name = frameId;
		}
		io.style.position = 'absolute';
		io.style.top = '-1000px';
		io.style.left = '-1000px';
		document.body.appendChild(io);
	},
	appendPostData: function (postData) {
		var formElements = new Array();
		var postMessage = postData.split('&');
		for (var i = 0; i < postMessage.length; i++) {
			var delimitPos = postMessage[i].indexOf('=');
			if (delimitPos != -1) {
				formElements[i] = document.createElement('input');
				formElements[i].type = 'hidden';
				formElements[i].name = postMessage[i].substring(0, delimitPos);
				formElements[i].value = postMessage[i].substring(delimitPos + 1);
				this._formNode.appendChild(formElements[i]);
			}
		}
		return formElements;
	},
	uploadFile: function (id, callback, uri, postData) {
		var frameId = 'yuiIO' + id;
		var io = document.getElementById(frameId);
		this._formNode.action = uri;
		this._formNode.method = 'POST';
		this._formNode.target = frameId;
		if (this._formNode.encoding) {
			this._formNode.encoding = 'multipart/form-data';
		}
		else {
			this._formNode.enctype = 'multipart/form-data';
		}
		if (postData) {
			var oElements = this.appendPostData(postData);
		}
		this._formNode.submit();
		if (oElements && oElements.length > 0) {
			try {
				for (var i = 0; i < oElements.length; i++) {
					this._formNode.removeChild(oElements[i]);
				}
			}
			catch (e) {}
		}
		this.resetFormState();
		var uploadCallback = function () {
			var obj = {};
			obj.tId = id;
			obj.argument = callback.argument;
			try {
				obj.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
				obj.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument : io.contentWindow.document;
			}
			catch (e) {}
			if (callback.upload) {
				if (!callback.scope) {
					callback.upload(obj);
				}
				else {
					callback.upload.apply(callback.scope, [obj]);
				}
			}
			if (YAHOO.util.Event) {
				YAHOO.util.Event.removeListener(io, "load", uploadCallback);
			}
			else if (window.detachEvent) {
				io.detachEvent('onload', uploadCallback);
			}
			else {
				io.removeEventListener('load', uploadCallback, false);
			}
			setTimeout(function () {
				document.body.removeChild(io);
			}, 100);
		};
		if (YAHOO.util.Event) {
			YAHOO.util.Event.addListener(io, "load", uploadCallback);
		}
		else if (window.attachEvent) {
			io.attachEvent('onload', uploadCallback);
		}
		else {
			io.addEventListener('load', uploadCallback, false);
		}
	},
	abort: function (o, callback, isTimeout) {
		if (this.isCallInProgress(o)) {
			o.conn.abort();
			window.clearInterval(this._poll[o.tId]);
			delete this._poll[o.tId];
			if (isTimeout) {
				delete this._timeOut[o.tId];
			}
			this.handleTransactionResponse(o, callback, true);
			return true;
		}
		else {
			return false;
		}
	},
	isCallInProgress: function (o) {
		if (o.conn) {
			return o.conn.readyState != 4 && o.conn.readyState != 0;
		}
		else {
			return false;
		}
	},
	releaseObject: function (o) {
		o.conn = null;
		o = null;
	}
};