var APF = {
	log : function(a) {
	}
};
APF.Namespace = {
	register : function(d) {
		var c = d.split(".");
		var a = window;
		for (var b = 0; b < c.length; b++) {
			if (typeof a[c[b]] == "undefined") {
				a[c[b]] = new Object()
			}
			a = a[c[b]]
		}
	}
};
APF.Namespace.register("life.map");
(function(j, c) {
	var a = j;
	var g = {
		list : '<li id="{% id %}"><i class="map-icon icon  {% icon %} "></i><p class="text"> {% title %} <span class="gray"> （{% detail %}） </span></p><span class="distance gray"> {% distance %} 米</span></li>',
		icon : '<div id="{% id %}" class="mark-icon-box"><i class="map-icon mark-icon {% icon %}"></i></div>',
		detail : '<div id="{% id %}" class="mark-tip" style="display:none"><i class="mark-tip-sign"></i><div class="title"><span>{% title %}</span><b>{% distance %}米</b></div>{%detail%}<i class="map-icon icon"></i></div>',
		pan : '<div class="life-mark"><div class="text yahei">{% panName %}</div><i class="map-icon icon"></i></div>'
	};
	var d = {
		bus : {
			icon : "bus"
		},
		subway : {
			icon : "met"
		},
		bank : {
			icon : "bank"
		},
		market : {
			icon : "buy"
		},
		restaurant : {
			icon : "bin"
		},
		hospital : {
			icon : "hos"
		},
		school : {
			icon : "sch"
		}
	};
	var e = {
		tra : ["subway", "bus"],
		biz : ["market", "bank", "restaurant"],
		hos : ["hospital"],
		edu : ["school"]
	};
	var h = {
		tra : {
			soj : "{from:loupan_index_traffic}",
			type : "2公里内的交通设施",
			subway : "{%num%}条地铁",
			bus : "{%num%}个公交站"
		},
		biz : {
			soj : "{from:loupan_index_commeric}",
			type : "2公里内的商业设施",
			market : "购物{%num%}处",
			bank : "银行{%num%}处",
			restaurant : "餐饮{%num%}处"
		},
		edu : {
			soj : "{from:loupan_index_education}",
			type : "2公里内的教育设施",
			school : "学校{%num%}处"
		},
		hos : {
			soj : "{from:loupan_index_hospital}",
			type : "2公里内的医院设施",
			hospital : "医院{%num%}处"
		}
	};
	function f(l, n) {
		var k = {
			tab : "tra",
			type : "subway",
			shift : {
				x : 14,
				y : 36
			},
			marker : "",
			done : function() {
			}
		};
		var m = c.extend({}, k, n);
		this._point = l;
		this.tab = m.tab;
		this.type = m.type;
		this.shift = m.shift;
		this.marker = m.marker;
		this.done = m.done
	}
	f.prototype = new BMap.Overlay();
	f.prototype.initialize = function(k) {
		this.map = k;
		this.marker = c(this.marker);
		k.getPanes().markerPane.appendChild(this.marker[0]);
		return this.marker[0]
	};
	f.prototype.draw = function() {
		var k = this.map.pointToOverlayPixel(this._point);
		this.marker.css({
					left : k.x - this.shift.x + "px",
					top : k.y - this.shift.y + "px"
				});
		this.done()
	};
	function b(q, m, p, n) {
		var l = {
			distance : 2000,
			keywords : [{
						key : "地铁",
						property : "subway"
					}, {
						key : "公交",
						property : "bus"
					}, {
						key : "购物",
						property : "market"
					}, {
						key : "银行",
						property : "bank"
					}, {
						key : "餐饮",
						property : "restaurant"
					}, {
						key : "学校",
						property : "school"
					}, {
						key : "医院",
						property : "hospital"
					}],
			success : q.success
		};
		var o = c.extend({}, l, p);
		function k(r, y, x) {
			var u = {}, w = [], v = [];
			for (var s = 0; s < x.keywords.length; s++) {
				w[s] = x.keywords[s].key;
				v[s] = x.keywords[s].property
			}
			var z = {
				onSearchComplete : function(D) {
					if (t.getStatus() == BMAP_STATUS_SUCCESS) {
						for (var B = 0; B < D.length; B++) {
							var A = D[B].keyword;
							var E = [];
							for (var C = 0; C < D[B].getCurrentNumPois(); C++) {
								E.push({
									title : D[B].getPoi(C).title,
									address : D[B].getPoi(C).address,
									distance : q.getDistance(
											D[B].getPoi(C).point, y).toFixed(0),
									point : D[B].getPoi(C).point,
									type : v[B]
								})
							}
							u[v[B]] = E
						}
					} else {
						u = {
							bank : [],
							bus : [],
							subway : [],
							market : [],
							restaurant : [],
							school : [],
							hospital : []
						}
					}
					n.nearbyData = u;
					x.success(u)
				},
				pageCapacity : 25
			};
			var t = new BMap.LocalSearch(q, z);
			t.searchNearby(w, m, o.distance)
		}
		return k(q, m, o)
	}
	a.Bmap = function(n) {
		var k = {
			panName : "观澜华庭",
			lng : 121.199244,
			lat : 31.299189,
			mapId : "map-box",
			initList : {
				type : "tra",
				include : ["subway", "bus"]
			}
		};
		var m = c.extend({}, k, n);
		var l = this;
		l.op = m;
		l.point = new BMap.Point(m.lng, m.lat);
		l.map = new BMap.Map("map-box");
		l.map.centerAndZoom(l.point, 14);
		l.map.addControl(new BMap.NavigationControl());
		l.success = function() {
			l.drawPan();
			l.drawList(l.nearbyData, l.op.initList);
			l.drawDetail();
			l.drawPoint();
			l.addHover();
			l.addControl();
			XF.Soj.send(h[l.op.initList.type].soj, XF.Soj.param)
		};
		b(l.map, l.point, {
					success : l.success
				}, l)
	};
	a.Bmap.prototype.drawPan = function() {
		var m = XF.render(g.pan, {
					panName : this.op.panName
				});
		var n = {
			shift : {
				x : 0,
				y : 38
			},
			marker : m,
			done : function() {
				var o = this;
				var p = o.marker.outerWidth();
				o.marker.css("marginLeft", Math.floor(0 - p / 2) + "px")
			}
		};
		var k = new f(this.point, n);
		var l = new BMap.Circle(this.point, 2000, {
					strokeColor : "#62ab00",
					strokeWeight : 1,
					strokeOpacity : 0.4
				});
		this.map.addOverlay(k);
		this.map.addOverlay(l)
	};
	a.Bmap.prototype.drawPoint = function() {
		for (var l = 0; l < this.listData.length; l++) {
			var m = this.listData[l].point;
			var o = "icon-" + l;
			var n = {
				tab : this.tab,
				type : this.listData[l].type,
				shift : {
					x : 14,
					y : 36
				},
				marker : XF.render(g.icon, {
							icon : d[this.listData[l].type].icon,
							id : o
						}),
				done : function() {
				}
			};
			var k = new f(m, n);
			this.map.addOverlay(k)
		}
	};
	a.Bmap.prototype.drawList = function(l, m) {
		var n = [];
		var q = "";
		var r = c("#life-list");
		var p = c("#life-list-count");
		var u = c("#life-list-type");
		var t = [];
		this.tab = m.type;
		for (var k = 0; k < m.include.length; k++) {
			n = n.concat(l[m.include[k]]);
			var o = l[m.include[k]].length;
			var s = h[m.type][m.include[k]];
			if (o > 0) {
				t.push(XF.render(s, {
							num : o
						}))
			}
		}
		this.listData = n.sort(function(w, x) {
					return w.distance - x.distance
				});
		if (n.length == 0) {
			u.html(h[m.type].type);
			p.html("暂无数据")
		} else {
			if (n.length > 0) {
				u.html(h[m.type].type);
				p.html(t.join("、"))
			}
		}
		for (var k = 0; k < n.length; k++) {
			var v = {
				id : "list-" + k,
				icon : d[n[k].type].icon,
				title : n[k].title,
				detail : n[k].address,
				distance : n[k].distance
			};
			q += XF.render(g.list, v)
		}
		r.html(q)
	};
	a.Bmap.prototype.drawDetail = function() {
		for (var l = 0; l < this.listData.length; l++) {
			var m = this.listData[l].point;
			var n = {
				tab : this.tab,
				type : this.listData[l].type,
				shift : {
					x : -120,
					y : 49
				},
				marker : XF.render(g.detail, {
							id : "detail-" + l,
							title : this.listData[l].title,
							distance : this.listData[l].distance,
							detail : this.tab == "tra"
									? '<div class="content">'
											+ this.listData[l].address
											+ "</div>"
									: ""
						}),
				done : function() {
					var o = this;
					var p = o.marker.outerWidth();
					var q = o.marker.outerHeight();
					o.marker.css({
								marginLeft : 0 - p / 2,
								marginTop : 0 - q
							})
				}
			};
			var k = new f(m, n);
			this.map.addOverlay(k)
		}
	};
	a.Bmap.prototype.addHover = function() {
		var k = this;
		c("#map-box").off("mouseover mouseout", ".mark-icon-box");
		c("#life-list").off("mouseover mouseout", "li");
		c("#map-box").on("mouseover mouseout", ".mark-icon-box", function(n) {
					var l = c("#"
							+ c(this).attr("id").replace(/icon/, "detail"));
					var m = c("#" + c(this).attr("id").replace(/icon/, "list"));
					if (n.type == "mouseover") {
						l.show();
						m.addClass("active");
						m.find(".text").css("color", "#ff6500")
					} else {
						l.hide();
						m.removeClass("active");
						m.find(".text").css("color", "#333")
					}
				});
		c("#life-list").on("mouseover mouseout", "li", function(n) {
					var m = c("#"
							+ c(this).attr("id").replace(/list/, "detail"));
					var l = c("#" + c(this).attr("id").replace(/list/, "icon"));
					if (n.type == "mouseover") {
						c(this).find(".text").css("color", "#ff6500");
						c(this).addClass("active");
						l.addClass("life-map-active");
						m.show()
					} else {
						c(this).find(".text").css("color", "#333");
						c(this).removeClass("active");
						l.removeClass("life-map-active");
						m.hide()
					}
				})
	};
	a.Bmap.prototype.addControl = function() {
		var k = this;
		c("#life-tab").on("click", "li", function(m) {
					if (!c(this).hasClass("active")) {
						var l = c(this).attr("id").replace(/life-tab-/, "");
						var n = c("#life-tab li.active");
						n.removeClass("active");
						c(this).addClass("active");
						k.map.clearOverlays();
						k.drawPan();
						k.drawList(k.nearbyData, {
									type : l,
									include : e[l]
								});
						k.drawDetail();
						k.drawPoint();
						k.addHover();
						XF.Soj.send(h[l].soj, XF.Soj.param)
					}
				})
	}
})(life.map, jQuery);
(function($) {
	var _aui = {};
	_aui.nameSpace = function(ns) {
		var nsParts = ns.split(".");
		var root = window;
		for (var i = 0; i < nsParts.length; i++) {
			if (typeof root[nsParts[i]] == "undefined") {
				root[nsParts[i]] = {}
			}
			root = root[nsParts[i]]
		}
	};
	_aui.Config = {
		devLogURL : "http://www.fang.anjuke.test/ts.html?",
		logURL : "http://www.fang.anjuke.com/ts.html?",
		isDev : /dev|test/.test(document.domain),
		blackList : ["BdPlayer", "baiduboxapphomepagetag"]
	};
	function isblack(str) {
		var i, reg, length, blackList = _aui.Config.blackList;
		if (typeof str !== "string") {
			return true
		}
		for (i = 0, length = blackList.length; i < length; i++) {
			reg = new RegExp(blackList[i], "g");
			if (reg.test(str)) {
				return true
			}
		}
	}
	function log(params) {
		var errorinfo = "tp=error&site=kfs&msg=", key, url, arr = [], image, msg;
		if (typeof params === "string") {
			msg = params
		}
		if (typeof params === "object") {
			for (key in params) {
				if (params.hasOwnProperty(key)) {
					arr.push(key + ":" + encodeURIComponent(params[key]))
				}
			}
			msg = arr.join(",")
		}
		if (isblack(msg)) {
			return false
		}
		image = new Image();
		if (_aui.Config.isDev) {
			url = _aui.Config.devLogURL + errorinfo + msg
		} else {
			url = _aui.Config.logURL + errorinfo + msg
		}
		image.src = url
	}
	window.onerror = function(msg, url, line) {
		log({
					message : msg,
					url : url,
					line : line
				})
	};
	_aui.inherit = function(my, classParent, args) {
		classParent.apply(my, args || []);
		$.extend(my.constructor.prototype, classParent.prototype)
	};
	_aui.Observer = function() {
		this._ob = {}
	};
	_aui.Observer.prototype.on = function(eventNames, callback) {
		var _events = eventNames.split(" ");
		var _eventKeys = {};
		for (var i = 0; i < _events.length; i++) {
			if (!this._ob[_events[i]]) {
				this._ob[_events[i]] = []
			}
			var _key = this._ob[_events[i]].push(callback);
			_eventKeys[_events[i]] = _key - 1
		}
		return _eventKeys
	};
	_aui.Observer.prototype.off = function(eventName, keys) {
		if (!!keys && !$.isArray(keys)) {
			keys = [keys]
		}
		if (this._ob[eventName]) {
			for (var i = 0; i < this._ob[eventName].length; i++) {
				if (!keys || $.inArray(i, keys)) {
					this._ob[eventName][i] = undefined
				}
			}
		}
	};
	_aui.Observer.prototype.trigger = function(eventName, args) {
		var r;
		if (!this._ob[eventName]) {
			return r
		}
		var _arg = args || [];
		for (var i = 0; this._ob[eventName] && i < this._ob[eventName].length; i++) {
			if (!this._ob[eventName][i]) {
				continue
			}
			var _r = this._ob[eventName][i].apply(this, _arg);
			r = (r === false) ? r : _r
		}
		return r
	};
	_aui.Observer.prototype.one = function(eventName, callback) {
		var self = this;
		var key = self.on(eventName, function() {
					callback.apply(this, arguments);
					self.off(eventName, key)
				})
	};
	_aui.render = function(tpl, data, op) {
		var daName = [], daVal = [], efn = [], _fnBuf, _op = $.extend({},
				_aui.render._options, op || {});
		for (var i in data) {
			daName.push(i);
			daVal.push("data." + i)
		}
		var _tp = tpl.replace(new RegExp(_op.open, "g"), _op.open + _op.val);
		_fnBuf = _tp.split(new RegExp(_op.open + "|" + _op.close, "g"));
		for (var i = 0; i < _fnBuf.length; i++) {
			if (new RegExp("^" + _op.val + _op.exp).test(_fnBuf[i])) {
				_fnBuf[i] = _fnBuf[i].replace(new RegExp("^" + _op.val
								+ _op.exp), "")
			} else {
				if (_fnBuf[i].length > 0) {
					if (new RegExp("^" + _op.val).test(_fnBuf[i])) {
						_fnBuf[i] = "_buf.push("
								+ _fnBuf[i].replace(new RegExp("^" + _op.val),
										"") + ");"
					} else {
						_fnBuf[i] = "_buf.push('" + _fnBuf[i] + "');"
					}
				}
			}
		}
		efn.push("(function(");
		efn.push(daName.join(","));
		efn.push("){");
		efn.push("var _buf = [];");
		efn.push(_fnBuf.join(""));
		efn.push('return _buf.join("")');
		efn.push("})(");
		efn.push(daVal.join(","));
		efn.push(")");
		return eval(efn.join(""))
	};
	_aui.render._options = {
		open : "{%",
		close : "%}",
		exp : ">",
		val : "="
	};
	_aui.nameSpace("XF");
	window.XF = _aui
})(jQuery);
(function(a) {
	XF.nameSpace("Loupan.Map");
	Loupan.Map = function(c) {
		var b = new life.map.Bmap(c)
	}
})(jQuery);
(function($) {
	XF.nameSpace("XF.Vars");
	XF.nameSpace("XF.Validate");
	XF.nameSpace("XF.WindowsOpen");
	XF.Validate.phoneMobile = function(data) {
		return /^1[3|4|5|7|8]\d{9}$/.test(data)
	};
	XF.Validate.phoneArea = function(data) {
		return /^0\d{2,3}$/.test(data)
	};
	XF.Validate.phonePlane = function(data) {
		return /^[2-9]\d{6,7}$/.test(data)
	};
	XF.Validate.email = function(data) {
		return /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(data)
	};
	XF.Validate.smsCode = function(data) {
		return /^\d{4}$/.test(data)
	};
	XF.WindowsOpen.redirect = function(url) {
		if (!
		/* @cc_on!@ */
		0) {
			window.open(url, "_blank")
		} else {
			var a = document.createElement("a");
			a.href = url;
			a.target = "_blank";
			document.body.appendChild(a);
			a.click()
		}
	}
})(jQuery);
XF.nameSpace("XF.Soj.send");
XF.nameSpace("XF.Soj.param");
(function(a) {
	XF.Soj.send = function(g, j, b) {
		var e = a.extend({}, {
					Site : "anjuke-npv"
				}, j || {});
		var c = new SiteTracker();
		for (var d in e) {
			c["set" + d](e[d])
		}
		var h = function() {
			var k = "";
			try {
				k = window.top.document.referrer
			} catch (m) {
				if (window.parent) {
					try {
						k = window.parent.document.referrer
					} catch (l) {
						k = ""
					}
				}
			}
			if (k === "") {
				k = document.referrer
			}
			return k
		};
		c.setReferer(h());
		c.setCustomParam(g);
		c.track(b);
		if (!/npv/.test(e.Site)) {
			var f = c.getParams();
			delete f.cp;
			delete f.sc;
			window._trackURL = JSON.stringify(f);
			a.getScript("http://tracklog.58.com/referrer_anjuke_pc.js",
					function() {
						c.setSite(j.Site + "-npv");
						c.setPageName(j.PageName + "_tracklog");
						c.setPage(j.Page + "_tracklog");
						c.track()
					})
		}
	};
	a("a[soj]").each(function(g, d) {
		var f = a(d), b = f.attr("href") || "", c = encodeURIComponent(f
				.attr("soj"));
		if (/from=/.test(b)) {
			return
		}
		b = b.split("#");
		var e = (/\?/.test(b[0])) ? "&from=" + c : "?from=" + c;
		if (b.length > 1) {
			e += "#";
			f.attr("href", b.join(e))
		} else {
			f.attr("href", b[0] + e)
		}
	})
})(jQuery);
function init_data_sojcommon(a) {
	if (typeof a !== "undefined") {
		jQuery("*[data-sojcommon]").on("click.sendsoj", function() {
					var b = "{from:" + jQuery(this).data("sojcommon") + "}";
					XF.Soj.send(b, a)
				})
	}
}

var SiteTracker = function(s, p, r, u) {
	if (s != undefined && s != null) {
		this.site = s;
	}

	if (p != undefined && p != null) {
		this.page = p;
	}

	if (r != undefined && r != null) {
		this.referer = r;
	}

	if (u != undefined && u != null) {
		this.uid = u;
	}

	this.serial = 0;
};

SiteTracker.prototype.getCookie = function(sKey) {
	if (!sKey || !this.hasItem(sKey)) {
		return null;
	}
	return decodeURIComponent(document.cookie.replace(
			new RegExp("(?:^|.*;\\s*)"
					+ encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&")
					+ "\\s*\\=\\s*((?:[^;](?!;))*[^;]?).*"), "$1"));
};

SiteTracker.prototype.hasItem = function(sKey) {
	return (new RegExp("(?:^|;\\s*)"
			+ encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&")
			+ "\\s*\\=")).test(document.cookie);
};

SiteTracker.prototype.track = function(t_params) {
	this.buildParams();

	var src = "";

	if (typeof(t_params) == "undefined"
			|| typeof(t_params.target_url) == "undefined") {
		src = "http://s.anjuke.com/stb?__site=" + this.params['site'] + "&";
	} else {
		src = t_params.target_url;
	}

	var prev_if = document.getElementById("sojtracker" + this.serial);
	while (prev_if) {
		this.serial += 1;
		prev_if = document.getElementById("sojtracker" + this.serial);
	}

	var ifContainer = document.createElement("div");
	ifContainer.innerHTML = '<iframe style="display:none" id="sojtracker'
			+ this.serial + '" name="sojtracker' + this.serial
			+ '" height="300" width="500"></iframe>';
	(document.getElementsByTagName('head')[0]).appendChild(ifContainer);

	var form = document.createElement("form");
	form.action = src;
	form.method = "post";
	for (var k in this.params) {
		if (k == "uid") {
			form.innerHTML += "<input type='hidden' name='" + k + "' value='"
					+ (this.params[k] || 0) + "' />";
		} else {
			form.innerHTML += "<input type='hidden' name='" + k + "' value='"
					+ (this.params[k] || "") + "' />";
		}
	}
	(document.getElementsByTagName('head')[0]).appendChild(form);
	form.target = "sojtracker" + this.serial;
	form.submit();
};

SiteTracker.prototype.buildParams = function() {
	var href = document.location.href;

	var guid = this.getCookie(this.nGuid || "aQQ_ajkguid");
	var ctid = this.getCookie(this.nCtid || "ctid");
	var luid = this.getCookie(this.nLiu || "lui");
	var ssid = this.getCookie(this.nSessid || "sessid");
	var uid = this.getCookie(this.nUid || "ajk_member_id");

	if (this.uid != undefined && this.uid != null) {
		uid = this.uid;
	}

	if (uid == undefined || uid == null || uid == "") {
		uid = 0;
	}

	var method = "";
	if (this.method != undefined && this.method != null) {
		method = this.method;
	}

	this.params = new Object();
	this.params.p = this.page;
	this.params.h = href;
	this.params.r = this.referer;
	this.params.site = this.site;
	this.params.guid = guid;
	this.params.ssid = ssid;
	this.params.uid = uid;
	this.params.t = new Date().getTime();
	this.params.ctid = ctid;
	this.params.luid = luid;
	this.params.m = method;

	if (this.screen != undefined) {
		this.params.sc = JSON.stringify(this.screen)
	}

	if (this.cst != undefined && /[0-9]{13}/.test(this.cst)) {
		this.params.lt = this.params.t - parseInt(this.cst);
	}

	if (this.pageName != undefined) {
		this.params.pn = this.pageName;
	}

	if (this.customParam != undefined) {
		this.params.cp = this.customParam;
	}

};

SiteTracker.prototype.setSite = function(s) {
	this.site = s;
};

SiteTracker.prototype.setPage = function(p) {
	this.page = p;
};

SiteTracker.prototype.setPageName = function(n) {
	this.pageName = n;
};

SiteTracker.prototype.setCookieNames = function(c) {
	this.cookNames = c;
};

SiteTracker.prototype.setReferer = function(r) {
	this.referer = r;
};

SiteTracker.prototype.setUid = function(u) {
	this.uid = u;
};

SiteTracker.prototype.setMethod = function(m) {
	this.method = m;
};

SiteTracker.prototype.setNGuid = function(n) {
	this.nGuid = n;
};

SiteTracker.prototype.setNCtid = function(n) {
	this.nCtid = n;
};

SiteTracker.prototype.setNLiu = function(n) {
	this.nLiu = n;
};

SiteTracker.prototype.setNSessid = function(n) {
	this.nSessid = n;
};

SiteTracker.prototype.setNUid = function(n) {
	this.nUid = n;
};

SiteTracker.prototype.setCst = function(n) {
	this.cst = n;
};

SiteTracker.prototype.setScreen = function(v) {
	this.screen = v;
};

SiteTracker.prototype.setCustomParam = function(v) {
	this.customParam = v;
}
SiteTracker.prototype.getParams = function() {
	return this.params;
}