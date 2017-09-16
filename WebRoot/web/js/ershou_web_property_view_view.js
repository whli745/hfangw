J.ready(function() {
			function a(g, f) {
				var c = J.g(g), e = J.g(f), d, b = 500;
				c.on("click", function() {
							if (e.getStyle("display") == "none") {
								e.show()
							} else {
								e.hide()
							}
						});
				J.g("topic").on("mouseover", function() {
							if (e.getStyle("display") != "none") {
								window.clearTimeout(d);
								e.show()
							}
						});
				J.g("topic").on("mouseout", function() {
							if (e.getStyle("display") != "none") {
								window.clearTimeout(d);
								d = window.setTimeout(function() {
											e.hide()
										}, b)
							}
						});
				e.on("mouseover", function() {
							window.clearTimeout(d);
							e.show()
						});
				e.on("mouseout", function() {
							window.clearTimeout(d);
							d = window.setTimeout(function() {
										e.hide()
									}, b)
						})
			}
			J.globalFooter = {};
			J.globalFooter.FooterCityList = a
		});
J.ready(function() {
			b();
			c();
			J.g("weixin").on("mouseenter", function() {
						var h = J.g("j_erweima");
						h.removeClass("fadeOut");
						h.addClass("fadeIn")
					});
			J.g("weixin").on("mouseleave", function() {
						var h = J.g("j_erweima");
						h.removeClass("fadeIn");
						h.addClass("fadeOut")
					});
			J.g("weixin").on("click", function(h) {
						return false
					});
			J.on(window, "scroll", function() {
						b()
					});
			J.on(window, "resize", function() {
						c()
					});
			J.g("backTop").on("click", function() {
						if (document.documentElement.scrollTop > 0) {
							document.documentElement.scrollTop = 0
						} else {
							if (document.body.scrollTop > 0) {
								document.body.scrollTop = 0
							}
						}
						_scrollTop = 0;
						J.site.trackEvent("click_backTop")
					});
			function b() {
				var h = document.documentElement.scrollTop
						|| document.body.scrollTop;
				if (h >= 400) {
					J.g("backTop").setStyle({
								visibility : "visible"
							})
				} else {
					J.g("backTop").setStyle({
								visibility : "hidden"
							})
				}
			}
			function f() {
				var h = J.g("j_erweima").attr("data-hide");
				if (h != "1") {
					J.g("j_erweima").attr({
								"data-hide" : "1"
							});
					J.g("j_erweima").hide();
					J.site.trackEvent("hide_erweima")
				} else {
					J.g("j_erweima").attr({
								"data-hide" : "0"
							});
					J.g("j_erweima").show()
				}
			}
			function d(h, j) {
				if (document.all) {
					window.external.addFavorite(h, j)
				} else {
					alert("抱歉，您的浏览器不支持此操作。\n\n收藏失败，请使用Ctrl+D添加至浏览器哦！")
				}
			}
			function c() {
				var h = 1380;
				if (document.documentElement.clientWidth < h) {
					J.g("j_erweima").addClass("j_sidebar_hide");
					J.g("weixin").hide();
					J.g("favirote").hide();
					J.g("suggestion").hide();
					J.g("weiliao").hide();
					J.g("research").hide()
				} else {
					J.g("j_erweima").removeClass("j_sidebar_hide");
					J.g("weixin").show();
					J.g("favirote").show();
					J.g("suggestion").show();
					J.g("weiliao").show();
					J.g("research").show()
				}
			}
			function a() {
				var j = urlForWeiLiaoJs;
				J.get({
							url : j,
							data : {
								jsonp : 1,
								jsonpcallback : "setUnreadMsgCount"
							},
							timeout : 20000,
							type : "jsonp"
						});
				window.setUnreadMsgCount = h;
				function h(l) {
					if (l.retcode == 0 && l.retdata && l.retdata.unreadMsgNum) {
						var k = l.retdata.unreadMsgNum;
						if (k > 0) {
							k = (parseInt(k) > 99) ? "99+" : k;
							J.g("unReadNum").html(k);
							J.g("unReadBg").show()
						} else {
							J.g("unReadBg").hide()
						}
					}
				}
			}
			J.g("weiliao").length && a();
			var g = 0;
			var e = 0;
			J.g("weiliao").length && (function() {
				J.g("weiliao").on("click", function() {
							e = new Date().getTime();
							if (g == 0 || (e - g) > 500) {
								J.chat.opener.open()
							}
							g = e
						})
			}.require(["chat.opener"], true));
			J.g("research").length && J.g("research").on("click", function() {
						J.site.trackEvent("click_research")
					})
		});
var SiteTracker = function(b, d, c, a) {
	if (b != undefined && b != null) {
		this.site = b
	}
	if (d != undefined && d != null) {
		this.page = d
	}
	if (c != undefined && c != null) {
		this.referer = c
	}
	if (a != undefined && a != null) {
		this.uid = a
	}
	this.serial = 0
};
SiteTracker.prototype.getCookie = function(a) {
	if (!a || !this.hasItem(a)) {
		return null
	}
	return decodeURIComponent(document.cookie.replace(
			new RegExp("(?:^|.*;\\s*)"
					+ encodeURIComponent(a).replace(/[\-\.\+\*]/g, "\\$&")
					+ "\\s*\\=\\s*((?:[^;](?!;))*[^;]?).*"), "$1"))
};
SiteTracker.prototype.hasItem = function(a) {
	return (new RegExp("(?:^|;\\s*)"
			+ encodeURIComponent(a).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\="))
			.test(document.cookie)
};
SiteTracker.prototype.track = function(b) {
	this.buildParams();
	var f = "";
	if (typeof(b) == "undefined" || typeof(b.target_url) == "undefined") {
		f = "http://s.anjuke.com/stb?__site=" + this.params.site + "&"
	} else {
		f = b.target_url
	}
	var e = document.getElementById("sojtracker" + this.serial);
	while (e) {
		this.serial += 1;
		e = document.getElementById("sojtracker" + this.serial)
	}
	var c = document.createElement("div");
	c.innerHTML = '<iframe style="display:none" id="sojtracker' + this.serial
			+ '" name="sojtracker' + this.serial
			+ '" height="300" width="500"></iframe>';
	(document.getElementsByTagName("head")[0]).appendChild(c);
	var d = document.createElement("form");
	d.action = f;
	d.method = "post";
	for (var a in this.params) {
		if (a == "uid") {
			d.innerHTML += "<input type='hidden' name='" + a + "' value='"
					+ (this.params[a] || 0) + "' />"
		} else {
			d.innerHTML += "<input type='hidden' name='" + a + "' value='"
					+ (this.params[a] || "") + "' />"
		}
	}
	(document.getElementsByTagName("head")[0]).appendChild(d);
	d.target = "sojtracker" + this.serial;
	d.submit()
};
SiteTracker.prototype.buildParams = function() {
	var a = document.location.href;
	var b = this.getCookie(this.nGuid || "aQQ_ajkguid");
	var e = this.getCookie(this.nCtid || "ctid");
	var g = this.getCookie(this.nLiu || "lui");
	var d = this.getCookie(this.nSessid || "sessid");
	var c = this.getCookie(this.nUid || "ajk_member_id");
	if (this.uid != undefined && this.uid != null) {
		c = this.uid
	}
	if (c == undefined || c == null || c == "") {
		c = 0
	}
	var f = "";
	if (this.method != undefined && this.method != null) {
		f = this.method
	}
	this.params = new Object();
	this.params.p = this.page;
	this.params.h = a;
	this.params.r = this.referer;
	this.params.site = this.site;
	this.params.guid = b;
	this.params.ssid = d;
	this.params.uid = c;
	this.params.t = new Date().getTime();
	this.params.ctid = e;
	this.params.luid = g;
	this.params.m = f;
	if (this.screen != undefined) {
		this.params.sc = JSON.stringify(this.screen)
	}
	if (this.cst != undefined && /[0-9]{13}/.test(this.cst)) {
		this.params.lt = this.params.t - parseInt(this.cst)
	}
	if (this.pageName != undefined) {
		this.params.pn = this.pageName
	}
	if (this.customParam != undefined) {
		this.params.cp = this.customParam
	}
};
SiteTracker.prototype.setSite = function(a) {
	this.site = a
};
SiteTracker.prototype.setPage = function(a) {
	this.page = a
};
SiteTracker.prototype.setPageName = function(a) {
	this.pageName = a
};
SiteTracker.prototype.setCookieNames = function(a) {
	this.cookNames = a
};
SiteTracker.prototype.setReferer = function(a) {
	this.referer = a
};
SiteTracker.prototype.setUid = function(a) {
	this.uid = a
};
SiteTracker.prototype.setMethod = function(a) {
	this.method = a
};
SiteTracker.prototype.setNGuid = function(a) {
	this.nGuid = a
};
SiteTracker.prototype.setNCtid = function(a) {
	this.nCtid = a
};
SiteTracker.prototype.setNLiu = function(a) {
	this.nLiu = a
};
SiteTracker.prototype.setNSessid = function(a) {
	this.nSessid = a
};
SiteTracker.prototype.setNUid = function(a) {
	this.nUid = a
};
SiteTracker.prototype.setCst = function(a) {
	this.cst = a
};
SiteTracker.prototype.setScreen = function(a) {
	this.screen = a
};
SiteTracker.prototype.setCustomParam = function(a) {
	this.customParam = a
};
SiteTracker.prototype.getParams = function() {
	return this.params
};
if (typeof JSON !== "object") {
	JSON = {}
}
(function() {
	var rx_one = /^[\],:{}\s]*$/, rx_two = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, rx_three = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, rx_four = /(?:^|:|,)(?:\s*\[)+/g, rx_escapable = /[\\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, rx_dangerous = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
	function f(n) {
		return n < 10 ? "0" + n : n
	}
	function this_value() {
		return this.valueOf()
	}
	if (typeof Date.prototype.toJSON !== "function") {
		Date.prototype.toJSON = function() {
			return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-"
					+ f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate())
					+ "T" + f(this.getUTCHours()) + ":"
					+ f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds())
					+ "Z" : null
		};
		Boolean.prototype.toJSON = this_value;
		Number.prototype.toJSON = this_value;
		String.prototype.toJSON = this_value
	}
	var gap, indent, meta, rep;
	function quote(string) {
		rx_escapable.lastIndex = 0;
		return rx_escapable.test(string) ? '"'
				+ string.replace(rx_escapable, function(a) {
							var c = meta[a];
							return typeof c === "string" ? c : "\\u"
									+ ("0000" + a.charCodeAt(0).toString(16))
											.slice(-4)
						}) + '"' : '"' + string + '"'
	}
	function str(key, holder) {
		var i, k, v, length, mind = gap, partial, value = holder[key];
		if (value && typeof value === "object"
				&& typeof value.toJSON === "function") {
			value = value.toJSON(key)
		}
		if (typeof rep === "function") {
			value = rep.call(holder, key, value)
		}
		switch (typeof value) {
			case "string" :
				return quote(value);
			case "number" :
				return isFinite(value) ? String(value) : "null";
			case "boolean" :
			case "null" :
				return String(value);
			case "object" :
				if (!value) {
					return "null"
				}
				gap += indent;
				partial = [];
				if (Object.prototype.toString.apply(value) === "[object Array]") {
					length = value.length;
					for (i = 0; i < length; i += 1) {
						partial[i] = str(i, value) || "null"
					}
					v = partial.length === 0 ? "[]" : gap
							? "[\n" + gap + partial.join(",\n" + gap) + "\n"
									+ mind + "]"
							: "[" + partial.join(",") + "]";
					gap = mind;
					return v
				}
				if (rep && typeof rep === "object") {
					length = rep.length;
					for (i = 0; i < length; i += 1) {
						if (typeof rep[i] === "string") {
							k = rep[i];
							v = str(k, value);
							if (v) {
								partial.push(quote(k) + (gap ? ": " : ":") + v)
							}
						}
					}
				} else {
					for (k in value) {
						if (Object.prototype.hasOwnProperty.call(value, k)) {
							v = str(k, value);
							if (v) {
								partial.push(quote(k) + (gap ? ": " : ":") + v)
							}
						}
					}
				}
				v = partial.length === 0 ? "{}" : gap ? "{\n" + gap
						+ partial.join(",\n" + gap) + "\n" + mind + "}" : "{"
						+ partial.join(",") + "}";
				gap = mind;
				return v
		}
	}
	if (typeof JSON.stringify !== "function") {
		meta = {
			"\b" : "\\b",
			"\t" : "\\t",
			"\n" : "\\n",
			"\f" : "\\f",
			"\r" : "\\r",
			'"' : '\\"',
			"\\" : "\\\\"
		};
		JSON.stringify = function(value, replacer, space) {
			var i;
			gap = "";
			indent = "";
			if (typeof space === "number") {
				for (i = 0; i < space; i += 1) {
					indent += " "
				}
			} else {
				if (typeof space === "string") {
					indent = space
				}
			}
			rep = replacer;
			if (replacer
					&& typeof replacer !== "function"
					&& (typeof replacer !== "object" || typeof replacer.length !== "number")) {
				throw new Error("JSON.stringify")
			}
			return str("", {
						"" : value
					})
		}
	}
	if (typeof JSON.parse !== "function") {
		JSON.parse = function(text, reviver) {
			var j;
			function walk(holder, key) {
				var k, v, value = holder[key];
				if (value && typeof value === "object") {
					for (k in value) {
						if (Object.prototype.hasOwnProperty.call(value, k)) {
							v = walk(value, k);
							if (v !== undefined) {
								value[k] = v
							} else {
								delete value[k]
							}
						}
					}
				}
				return reviver.call(holder, key, value)
			}
			text = String(text);
			rx_dangerous.lastIndex = 0;
			if (rx_dangerous.test(text)) {
				text = text.replace(rx_dangerous, function(a) {
							return "\\u"
									+ ("0000" + a.charCodeAt(0).toString(16))
											.slice(-4)
						})
			}
			if (rx_one.test(text.replace(rx_two, "@").replace(rx_three, "]")
					.replace(rx_four, ""))) {
				j = eval("(" + text + ")");
				return typeof reviver === "function" ? walk({
							"" : j
						}, "") : j
			}
			throw new SyntaxError("JSON.parse")
		}
	}
}());
J.ready(function() {
			function a(j, e, g) {
				var f = J.g(j), b = J.g(e), g = g, k, c;
				f.on("mouseover", function(l) {
							h(l)
						});
				f.on("mouseout", function() {
							d()
						});
				b.on("mouseover", function(l) {
							h(l)
						});
				b.on("mouseout", function() {
							d()
						});
				function h(l) {
					l.stop();
					window.clearTimeout(k);
					b.show()
				}
				function d() {
					window.clearTimeout(k);
					k = window.setTimeout(function() {
								b.hide()
							}, g)
				}
			}
			J.globalCitySelector = {};
			J.globalCitySelector = a
		});
(function() {
	var b = "_soj", j = "_spd", c = "system-link-track", g = J.s("." + c);
	function d(k) {
		if (k.tagName === "A") {
			if (k.getAttribute("href")
					&& (k.getAttribute(b) || k.getAttribute(j))) {
				return true
			}
		}
	}
	function h(q) {
		var l = q.href, m = l.split("?"), s = m.length, o = (s > 1) ? m[1] : l, t = o
				.split("#"), n = (s > 1) ? "&" : "?", k = r(q), p = (k)
				? n + k
				: "";
		function r(x) {
			var v = x.href, A = x.getAttribute(b), w = x.getAttribute(j), u = /[?&]from=/, z = /[?&]spread=/, y = [];
			if (!u.test(v) && A) {
				y.push("from=" + encodeURIComponent(A))
			}
			if (!z.test(v) && w) {
				y.push("spread=" + encodeURIComponent(w))
			}
			return y.join("&")
		}
		if (s > 1 && t.length > 1) {
			q.href = m[0] + "?" + t[0] + p + "#" + t[1]
		} else {
			q.href += p
		}
	}
	function e(k) {
		k.setAttribute(b, "");
		k.setAttribute(j, "")
	}
	function f(k) {
		if (d(k)) {
			h(k)
		}
	}
	J.s("body").on("click", function(l) {
				var k = l.srcElement ? l.srcElement : l.target;
				f(k)
			});
	function a(m) {
		var l = m.currentTarget, k = J.s("a", l), n = new RegExp(c);
		if (!n.test(l.className)) {
			return
		}
		if (k.length) {
			J.each(k, function(o, p) {
						f(p[0])
					})
		}
		l.className = l.className.replace(c, "")
	}
	J.each(g, function(k, l) {
				l.on("click", a)
			})
})();
J.ready(function() {
			function b() {
				a()
			}
			b();
			function a() {
				J.s(".li_unselected").each(function(f, d) {
					var g = d.down(), e = d.hasClass("li_itemsnew")
							? d.down(1)
							: null;
					d.on("mouseenter", function() {
								d.addClass("li_hover");
								g.addClass("a_nav_hover");
								e && e.show()
							});
					d.on("mouseleave", function() {
								d.removeClass("li_hover");
								g.removeClass("a_nav_hover");
								e && e.hide()
							})
				});
				var c = J.s(".arrow_upnew");
				c.length && c.each(function(e, d) {
							var f = d.up().up(), g = 0;
							f && (g = (f.width() - 14) / 2);
							d.get().style.left = g + "px"
						});
				J.g("tab_58yzfy").on("click", function() {
							var d = new J.logger.Tracker("anjuke-npv");
							d.setPage("homepage_esf_navigation");
							d.setPageName("homepage_esf_navigation");
							d.setReferrer(document.referrer);
							d.setNGuid("aQQ_ajkguid");
							d.setNUid("ajk_member_id");
							try {
								d.track()
							} catch (e) {
								console.log(e)
							}
						})
			}
		});
(function() {
	J.g("header").s(".li_selected").each(function(b, a) {
				var c = a.s(".sec_divnew").length && a.s(".sec_divnew").eq(0);
				a.on("mouseenter", function(d) {
							c && c.show();
							d.stop()
						}).on("mouseleave", function(d) {
							c && c.hide();
							d.stop()
						})
			})
})();
J.ready(function() {
			var j = J.g("footseo-boxer");
			var c = J.g("footer_seo_tab") || false;
			doms = c.s("li");
			c && doms.each(function(m, l) {
						l.on("mouseenter", function() {
									var k = l.attr("rel");
									J.s(".footseo_list").each(function(o, n) {
												n.hide()
											});
									doms.each(function(o, n) {
												n.removeClass("tab_on")
											});
									l.addClass("tab_on");
									J.g("footseo_list_" + k).show()
								})
					});
			var j = J.g("footseo-boxer");
			var d = j.s(".footseo_list_first").eq(0);
			var f = d.s("li");
			var e = j.s(".footseo_list_second").eq(0);
			var g = e.s("ul");
			f.each(function(l, k) {
						k.on("click", function(m) {
									m = window.event || m;
									m.preventDefault
											? m.preventDefault()
											: (m.returnValue == false);
									f.each(function(o, n) {
												n.removeClass("on")
											});
									g.each(function(o, p) {
												p.setStyle({
															display : "none"
														})
											});
									k.addClass("on");
									g.eq(l).setStyle({
												display : "block"
											})
								})
					});
			var b = J.g("footseo_list_1");
			if (b.length && b.down(1).html() == "") {
				J.g("tab_1").setStyle({
							display : "none"
						})
			}
			var a = J.s(".showcitymore");
			a.length && a.each(function(m, l) {
						l.on("click", function() {
									var k = l.attr("rel");
									J.s(".diff_footseo_list_li_" + k).each(
											function(o, n) {
												n.show()
											});
									J.g("hiddencitymore_" + k).show();
									l.hide()
								})
					});
			var h = J.s(".hiddencitymore");
			h.length && h.each(function(m, l) {
						l.on("click", function() {
									var k = l.attr("rel");
									J.s(".diff_footseo_list_li_" + k).each(
											function(o, n) {
												n.hide()
											});
									J.g("showcitymore_" + k).show();
									l.hide()
								})
					})
		});
(function() {
	if (AJK.SeoRecommend.type == 0) {
		var a = 1;
		var h = b("tab-" + a);
		f(h, a, 1)
	} else {
		if (AJK.SeoRecommend.type == 1) {
			var a = 2;
			var h = b("parent-" + a);
			c(h, a)
		}
	}
	function c(q, k) {
		for (var o = 0; o < q.length; o++) {
			var l = q[o];
			var p = l.getElementsByTagName("ul");
			var r = l.getElementsByTagName("li");
			if (p.length) {
				for (var m = 0; m < r.length; m++) {
					var n = r[m];
					if (d(n, "tab-" + k)) {
						(function(u, v, s, t) {
							t.onmouseover = function() {
								for (var j = 0; j < u.length; j++) {
									g(u[j], "btn-show")
								}
								for (var j = 0; j < v.length; j++) {
									g(v[j].getElementsByTagName("a")[0],
											"current-tab")
								}
								e(u[s], "btn-show");
								e(t.getElementsByTagName("a")[0], "current-tab")
							}
						})(p, r, m, n)
					}
				}
				c(p, k + 1)
			}
		}
	}
	function f(r, t, o) {
		if (r.length) {
			var j = new Array(), p = r[0].parentNode.getElementsByTagName("ul");
			for (var l = 0; l < p.length; l++) {
				d(p[l], "parent-" + (t + 1)) && j.push(p[l])
			}
			if (j.length) {
				for (var l = 0; l < r.length; l++) {
					var s = r[l];
					(function(y, w, u, n, x) {
						n.onmouseover = function() {
							for (var z = 0; z < y.length; z++) {
								g(y[z], "btn-show")
							}
							for (var z = 0; z < w.length; z++) {
								g(w[z].getElementsByTagName("a")[0],
										"current-tab")
							}
							e(y[u], "btn-show");
							e(n.getElementsByTagName("a")[0], "btn-show");
							if (x) {
								for (var v = 0; v < w.length; v++) {
									g(w[v].getElementsByTagName("a")[0], "cur")
								}
								e(n.getElementsByTagName("a")[0], "cur")
							}
						}
					})(j, r, l, s, o)
				}
				t++;
				for (var q = 0; q < j.length; q++) {
					var m = [];
					for (var k = 0; k < j[q].getElementsByTagName("li").length; k++) {
						d(j[q].getElementsByTagName("li")[k], "tab-" + t)
								&& m.push(j[q].getElementsByTagName("li")[k])
					}
					f(m, t, 0)
				}
			}
		}
	}
	function d(k, j) {
		return k.className.match(new RegExp("(\\s|^)" + j + "(\\s|$)"))
	}
	function e(k, j) {
		if (!d(k, j)) {
			k.className += " " + j
		}
	}
	function g(l, j) {
		if (d(l, j)) {
			var k = new RegExp("(\\s|^)" + j + "(\\s|$)");
			l.className = l.className.replace(k, "")
		}
	}
	function b(m) {
		if (document.getElementByClassName) {
			return document.getElementByClassName(m)
		}
		var k = document.getElementsByTagName("*");
		var j = [];
		for (var l = 0; l < k.length; l++) {
			if (d(k[l], m)) {
				j.push(k[l])
			}
		}
		return j
	}
})();
J.ready(function() {
	var a = J.s(".tocollect_ok");
	var u = J.s(".nocollect_ok");
	var n, p, t, h;
	var f = "";
	var o = "";
(function() {
		x();
		e();
		m();
		c()
	})();
	function x() {
		var z = J.g(favParams.uniqueId);
		J.g(document.body).append(z);
		n = J.g(favParams.contentId);
		p = J.g(favParams.popupId);
		t = J.g(favParams.uniqueId)
	}
	function e() {
		J.s(".func em").each(function(D, C) {
					C.on("mouseover", function() {
								C.addClass("yellow")
							}).on("mouseout", function() {
								C.removeClass("yellow")
							})
				});
		var A = J.s(".funList"), z = J.s(".otherFunctions"), B = J
				.s(".funCont");
		z.length && z.eq(0).on("mouseover", function() {
					A.length && A.eq(0).setStyle({
								display : "block"
							})
				});
		B.length && B.eq(0).on("mouseleave", function() {
					A.length && A.eq(0).setStyle({
								display : "none"
							})
				})
	}
	function m() {
		var A = user_anjuke + "ajax/favorite/check_favorite";
		var B = {
			type : add_favorite_param.ptype,
			fid : add_favorite_param.fid,
			time : Math.random()
		};
		J.get({
					type : "jsonp",
					url : A,
					data : B,
					callback : "usercenter_check_favorite_success"
				});
		function z(C) {
			if (C.code == 0) {
				l();
				user_favorite > 0 ? v(user_favorite) : ""
			} else {
				if (C.code == 1) {
					y()
				}
			}
		}
		window.usercenter_check_favorite_success = z
	}
	function l() {
		u.each(function(A, z) {
					z.hide()
				});
		a.each(function(A, z) {
					z.show()
				})
	}
	function y() {
		u.each(function(A, z) {
					z.show()
				});
		a.each(function(A, z) {
					z.hide()
				})
	}
	function v(B) {
		if (B == "112") {
			f = user_anjuke_login_112_url;
			o = user_anjuke_register_112_url
		} else {
			f = user_anjuke_login_111_url;
			o = user_anjuke_register_111_url
		}
		var C = user_anjuke + "ajax/favorite/add_favorite";
		B = (add_favorite_param.btn != undefined) ? add_favorite_param.btn : B;
		J.get({
					url : C,
					type : "jsonp",
					data : {
						htype : add_favorite_param.htype,
						type : add_favorite_param.ptype,
						button : B,
						fid : add_favorite_param.fid,
						time : Math.random()
					},
					callback : "usercenter_add_favorite_success"
				});
		window.usercenter_add_favorite_success = A;
		function A(D) {
			if (D.code == 0) {
				if (pro_type == 2 || pro_type == 3 || pro_type == 14
						|| pro_type == "") {
					z(D.user_id);
					y();
					loginObj && loginObj.getFavorite && loginObj.getFavorite()
				} else {
					if (pro_type == 1) {
						w(user_type);
						y();
						loginObj && loginObj.getFavorite
								&& loginObj.getFavorite()
					}
				}
			} else {
				if (D.code == 2) {
					d("enough")
				}
			}
		}
		function z(D) {
			var F = "";
			if (typeof(register_source_code) != "undefined") {
				F = "?from=" + register_source_code
			}
			d("add", D);
			J.s(".panel_def_close").each(function(H, G) {
						E(G)
					});
			J.s(".adds").each(function(H, G) {
						E(G)
					});
			b();
			k();
			function E(G) {
				G.on("click", function() {
							t.hide()
						})
			}
		}
	}
	function d(D, B) {
		var A = '<div class="outerborder"> <div style="display:block;" class="msg"> <p class="success">您的收藏已加满,登陆后可继续添加</p><a class="go-on" style="display:inline-block;" href="'
				+ f
				+ "&from="
				+ register_source_code
				+ '">立刻登陆</a><p class="fav_full_tip"><a style="display:inline-block;" href="'
				+ o
				+ "?from="
				+ register_source_code
				+ '">没有账号?10秒快速注册&gt;&gt;</a></p> <a href="javascript:;" class="panel_close"><i class="close"></i></a> </div> </div>';
		var C = '<div class="outerborder"> <div style="display:block;height:auto;" class="msg"> <p class="success"><i class="ico-success"></i>取消收藏成功！</p> <a href="javascript:;" class="panel_close"><i class="close"></i></a>  </div> </div>';
		if (B) {
			var F = '<div class="outerborder"> <!-- NewPop Start --> <div style="display:block;" class="msg"> <p class="success"><i class="ico-success"></i>您已收藏成功！<a href="'
					+ (add_favorite_param.view_favorate_url || "")
					+ '">查看收藏&gt;&gt;</a></p> <div><a href="#" class="adds" style="_display:inline-block;"></a></div> <!--<span class="close go-on">继续找房</span>--> <i class="close panel_def_close"></i> </div> </div>'
		} else {
			var z = J.s(".u_l").eq(0).attr("href");
			var F = '<div class="outerborder"> <!-- NewPop Start --> <div style="display:block;" class="msg"> <p class="success"><i class="ico-success"></i>您已收藏成功！<a href="'
					+ (add_favorite_param.view_favorate_url || "")
					+ '">查看收藏&gt;&gt;</a> </p><div style="height:25px;line-height:25px;margin-left:-60px;padding: 0 10px;width:313px;margin-bottom:5px;">该收藏仅在本设备保存，若需要永久保存并同步请<a href="'
					+ z
					+ '">登录</a></div><div><a href="#" class="adds" style="_display:inline-block;"></a></div> <!--<span class="close go-on">继续找房</span>--> <i class="close panel_def_close"></i> </div> </div>'
		}
		var E = "";
		if (D == "cancel") {
			E = C
		} else {
			if (D == "enough") {
				E = A
			} else {
				if (D == "add") {
					E = F
				}
			}
		}
		q(E);
		if (D == "cancel") {
			h = setTimeout(function() {
						t.hide()
					}, 3000)
		}
	}
	function q(z) {
		n.html(z);
		b();
		k();
		s()
	}
	function k() {
		t.hide();
		h && clearTimeout(h);
		t.show()
	}
	function s() {
		t.s(".close").each(function(A, z) {
					z.on("click", function() {
								t.hide()
							})
				})
	}
	function w(z) {
		J.get({
					url : "/ajax/setmarked/",
					data : {
						retval : 1,
						action : "insert",
						id : favParams.propID,
						type : favParams.type,
						r : Math.random()
					},
					onSuccess : function(B) {
						A(B)
					}
				});
		function A(B) {
			var C = B;
			if (C == "NOTLOGIN") {
				confirm.show();
				return
			}
			if (C != "ERROR") {
				q(C)
			}
		}
	}
	function c() {
		r();
		g();
		J.on(window, "resize", function() {
					b()
				});
		J.on(window, "scroll", function() {
					b()
				})
	}
	function r() {
		a.each(function(A, z) {
					z.on("click", function(C) {
								C.stop();
								var B = z.attr("_button");
								v(B);
								B == "112"
										? J.site.trackEvent("topFavorite")
										: J.site.trackEvent("pageFavorite")
							})
				})
	}
	function g() {
		u.each(function(A, z) {
					var B = z.s(".toolOper");
					B = B.length && B.eq(0);
					z.on("mouseover", function(C) {
								B && B.html("取消收藏")
							});
					z.on("mouseout", function(C) {
								B && B.html("已收藏")
							});
					j(z)
				})
	}
	function j(z) {
		var B = user_anjuke + "ajax/favorite/del_favorite";
		var C = {
			type : add_favorite_param.ptype,
			fid : add_favorite_param.fid,
			time : Math.random()
		};
		z.on("click", function(E) {
					E.stop();
					J.get({
								type : "jsonp",
								url : B,
								data : C,
								callback : "usercenter_cancel_favorite_success"
							});
					var D = z.attr("_button");
					D == "112" ? J.site.trackEvent("topFavorite") : J.site
							.trackEvent("pageFavorite")
				});
		function A(D) {
			if (D.code == 0) {
				d("cancel");
				l();
				loginObj && loginObj.getFavorite && loginObj.getFavorite()
			}
		}
		window.usercenter_cancel_favorite_success = A
	}
	function b() {
		var D = window.innerWidth
				? window.innerWidth
				: document.documentElement.clientWidth
						? document.documentElement.clientWidth
						: document.body.clientWidth, z = window.innerHeight
				? window.innerHeight
				: document.documentElement.clientHeight
						? document.documentElement.clientHeight
						: document.body.clientHeight, G = t.width(), A = t
				.height(), E = window.pageYOffset
				? window.pageYOffset
				: document.body.scrollTop
						? document.body.scrollTop
						: document.documentElement.scrollTop, F = window.pageXOffset
				? window.pageXOffset
				: document.body.scrollLeft
						? document.body.scrollLeft
						: document.documentElement.scrollLeft;
		var C = F + (D - G) * 0.5;
		var B = E + (z - A) * 0.33;
		t.setStyle({
					left : C + "px",
					top : B + "px"
				})
	}
});
J.ready(function() {
	function a(h) {
		var f = J.g(h);
		if (!f.length) {
			return
		}
		var c = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		function d() {
			var k = f.val();
			var j = {
				errno : 0,
				msg : "验证成功"
			};
			if (!k) {
				j.errno = 1;
				j.msg = "邮箱格式错误";
				e(j.msg);
				return
			} else {
				var k = f.val();
				if (!c.test(k)) {
					j.errno = 2, j.msg = "邮箱格式错误";
					e(j.msg);
					return
				} else {
					g()
				}
			}
		}
		function e(k) {
			var j = J.g("email-info");
			j.removeClass("val-right");
			j.addClass("val-wrong");
			j.html(k);
			J.g("email-info").show();
			return
		}
		function g() {
			var j = J.g("email-info");
			j.removeClass("val-wrong");
			j.addClass("val-right");
			j.html("");
			J.g("email-info").show();
			return
		}
		return {
			handler : d
		}
	}
	function b(h) {
		var e = J.g(h);
		if (!e.length) {
			return
		}
		var g = /^[0-9]{11}$/;
		function c() {
			var k = e.val();
			var j = {
				errno : 0,
				msg : "验证成功"
			};
			if (!k) {
				j.errno = 1;
				j.msg = "手机格式错误";
				d(j.msg);
				return
			} else {
				if (!g.test(k)) {
					j.errno = 2, j.msg = "手机格式错误";
					d(j.msg);
					return
				} else {
					f()
				}
			}
		}
		function d(k) {
			var j = J.g("phone-info");
			j.removeClass("val-right");
			j.addClass("val-wrong");
			j.html(k);
			J.g("phone-info").show();
			return
		}
		function f() {
			var j = J.g("phone-info");
			j.removeClass("val-wrong");
			j.addClass("val-right");
			j.html("");
			J.g("phone-info").show();
			return
		}
		return {
			handler : c
		}
	}
	J.s(".comm_subscribe").each(function(c, d) {
		d.on("click", function(j) {
			j.stop();
			var h = "";
			if (J.s(".panel_def").length > 0) {
				J.popup.close()
			}
			if (d.hasClass("email-icon")) {
				h = J.g("popup-rss-new-prop").html()
			} else {
				if (d.hasClass("phone-icon")) {
					h = J.g("popup-rss-change-price").html()
				}
			}
			J.popup = new J.ui.panel({
						modal : false,
						mask : false,
						close : true,
						width : 579,
						content : h,
						onClose : function() {
						}
					});
			J.s(".panel_def").eq(0).setStyle("z-index:999999");
			J.s(".tab-box ul li").each(function(e, k) {
						J.on(k, "mouseover", function() {
									J.s(".tab-box ul li").each(function(l, m) {
												m.removeClass("cur")
											});
									k.addClass("cur");
									J.s(".tab-content").each(function(l, m) {
												m.hide()
											});
									J.g(k.attr("id") + "-content").show()
								})
					});
			J.s(".popup-box .comfirm").each(function(e, k) {
				k.on("click", function() {
					j.stop();
					var n = "";
					var p = "";
					var s = null;
					var q = 1;
					if (0 === e) {
						n = J.g("phone-num").val();
						q = 2;
						s = J.g("phone-info");
						g.handler()
					} else {
						if (1 === e) {
							p = J.g("email-addr").val();
							q = 1;
							s = J.g("email-info");
							f.handler()
						}
					}
					if (s.hasClass("val-right")) {
						var m = J.g("from-page").val();
						var o = J.g("post-url").val() + "?r=" + Math.random();
						var l = J.g("subscribe_name").val();
						var r = "";
						if (l == "cp") {
							r = register_source_code_cp
						} else {
							if (l == "nh") {
								r = register_source_code_nh
							}
						}
						J.post({
							url : o,
							type : "json",
							async : false,
							data : {
								email : p,
								phone : n,
								subscribe_type : q,
								from_page : m,
								reg_from : r
							},
							onSuccess : function(t) {
								if (t && t.status === 0) {
									J.popup.setContent(J
											.g("popup-rssok-change-price")
											.html());
									J.popup.setAutoClose(3)
								} else {
									if (t && t.status === 5) {
										var u = J.g("email-info");
										u.removeClass("val-right");
										u.addClass("val-wrong");
										u.html(t.message);
										J.g("email-info").show()
									} else {
										if (t && t.status === 6) {
											var u = J.g("phone-info");
											u.removeClass("val-right");
											u.addClass("val-wrong");
											u.html(t.message);
											J.g("phone-info").show()
										} else {
											if (t && t.error_code === 3) {
												J.popup
														.setContent(J
																.g("popup-rssok-change-price")
																.html());
												J.s("#success-msg").eq(0)
														.html("您已订阅过!");
												J.popup.setAutoClose(3)
											} else {
												if (t && t.error_code === 1) {
													J.popup
															.setContent(J
																	.g("popup-rssok-change-price")
																	.html());
													var v = (q === 1)
															? "邮箱"
															: "手机";
													J
															.s("#success-msg")
															.eq(0)
															.html("您的"
																	+ v
																	+ "订阅量已超过10条上限");
													J.popup.setAutoClose(3)
												} else {
													J.popup
															.setContent(J
																	.g("popup-rssok-change-price")
																	.html());
													J
															.s("#rssok .popup-container")
															.eq(0)
															.html("您订阅本次失败，请重新订阅。");
													J.popup.setAutoClose(3)
												}
											}
										}
									}
								}
							}
						})
					}
				})
			});
			var f = new a("email-addr");
			var g = new b("phone-num")
		})
	})
});
!function() {
	var d = {
		box : $(".report-wrap"),
		sucBox : $(".report-success"),
		enter : $("#falseTxt"),
		close : $(".close-wrap"),
		img : $(".item-mod .codeImg"),
		refresh : $(".item-mod .refresh"),
		getCode : $(".report-wrap .getcode"),
		submit : $(".report-wrap .submit"),
		rpType : $(".report-wrap [name=report_type]"),
		description : $(".report-wrap [name=description]"),
		mobile : $(".report-wrap [name=mobile]"),
		imgCode : $(".report-wrap [name=captcha]"),
		verify_msg : $(".report-wrap [name=verify_msg]"),
		errBox : $(".report-wrap .err-msg"),
		codeBtn : $(".report-wrap .getcode")
	};
	vdOptions = {
		report_type : {
			empty : "请选择举报类型！"
		},
		mobile : {
			empty : "请填写手机号！",
			format : "请填写正确手机号！"
		},
		captcha : {
			empty : "请填写图片验证码！",
			format : "图片验证码错误！"
		},
		description : {
			empty : "请填写举报内容！",
			format : "举报内容不能为非法字符！",
			min : "至少输入20个文字",
			max : "最多输入100个文字"
		},
		verify_msg : {
			empty : "请输入短信验证码！",
			format : "手机验证码错误！"
		},
		fy_url : {
			format : "房源地址错误！"
		},
		fy_id : {
			format : "房源ID错误！"
		},
		common : {
			fail : "执行失败！"
		}
	};
	d.enter.on("click.enter", function() {
				j(d.box);
				d.box.show()
			});
	d.close.click(function() {
				d.box.hide();
				d.sucBox.hide()
			});
	d.refresh.click(function() {
				c()
			});
	d.refresh.click(function() {
				c()
			});
	d.getCode.click(function() {
				if ($(this).is(".btn-disable")) {
					return
				}
				if (!g("mobile")) {
					return
				}
				if (!g("captcha")) {
					return
				}
				h("", "", true);
				f()
			});
	d.submit.click(function() {
				if (!g("report_type")) {
					return
				}
				if (!g("description")) {
					return
				}
				if (!g("mobile")) {
					return
				}
				if (!g("verify_msg")) {
					return
				}
				h("", "", true);
				b()
			});
	$(window).resize(function() {
				j(d.box)
			});
	function c() {
		return ;
		var k = d.img.attr("src");
		d.img.attr("src", k.substring(0, k.indexOf("?r=")) + "?r="
						+ Math.random())
	}
	function g(k) {
		switch (k) {
			case "report_type" :
				if (!d.rpType.filter(":checked").length) {
					h(k, "empty");
					return false
				}
				break;
			case "description" :
				if (!$.trim(d.description.val()).length) {
					h(k, "empty");
					return false
				}
				if (/<script.*?>.*?<\/script>/ig.test(d.description.val())) {
					h(k, "format");
					return false
				}
				if (d.description.val().length < 20) {
					h(k, "min");
					return false
				}
				if (d.description.val().length > 100) {
					h(k, "max");
					return false
				}
				break;
			case "mobile" :
				if (!d.mobile.val().length) {
					h(k, "empty");
					return false
				}
				if (!/^1[3|4|5|7|8]\d{9}$/.test(d.mobile.val())) {
					h(k, "format");
					return false
				}
				break;
			case "captcha" :
				if (!d.imgCode.val().length) {
					h(k, "empty");
					return false
				}
				break;
			case "verify_msg" :
				if (!d.verify_msg.val().length) {
					h(k, "empty");
					return false
				}
				break;
			default :
		}
		return true
	}
	function f() {
		$.ajax({
					url : "/v3/ajax/complaint/sendverifycode/",
					data : {
						mobile : d.mobile.val(),
						captcha : d.imgCode.val()
					},
					type : "GET",
					dataType : "json"
				}).done(function(k) {
					if (k.code == 2) {
						h("captcha", "format")
					} else {
						if (k.code == 1) {
							h("mobile", "format")
						} else {
							if (k.code == 0) {
								d.codeBtn.addClass("btn-disable");
								a(60)
							}
						}
					}
				})
	}
	function b() {
		var k = $("link:first").attr("href");
		h("", "", true);
		var l = {
			property_url : k,
			property_id : k.substring(k.indexOf("/view/") + 7),
			report_type : e(),
			description : d.description.val(),
			mobile : d.mobile.val(),
			verify_msg : d.verify_msg.val()
		};
		$.ajax({
					url : "/v3/ajax/complaint/savecomplaint/",
					data : l,
					type : "GET",
					dataType : "json"
				}).done(function(m) {
					if (m.code == 6) {
						h("verify_msg", "format")
					} else {
						if (m.code == 7) {
							h("fy_id", "format")
						} else {
							if (m.code == 8) {
								h("fy_url", "format")
							} else {
								if (m.code == 0) {
									d.box.hide();
									d.sucBox.show();
									setTimeout(function() {
												d.sucBox.hide()
											}, 2000)
								} else {
									h("common", "fail")
								}
							}
						}
					}
				})
	}
	function e() {
		var k = "";
		d.rpType.filter(":checked").each(function() {
					k += $(this).val() + ","
				});
		return k.substring(0, k.length - 1)
	}
	function h(k, m, l) {
		l ? d.errBox.hide() : d.errBox.text(vdOptions[k][m]).show()
	}
	function a(k) {
		if (k > 1) {
			k = k - 1;
			d.codeBtn.text(k + "秒后再次获取");
			clearTimeout(window.cd);
			window.cd = setTimeout(function() {
						a(k)
					}, 1000)
		} else {
			d.codeBtn.removeClass("btn-disable").text("获取验证码")
		}
	}
	function j(m) {
		var k = document.documentElement.clientWidth;
		var o = document.documentElement.clientHeight;
		var n = m.height();
		var l = m.width();
		m.css({
			zIndex : 9999,
			position : "fixed",
			top : (o - n)
					/ 2
					+ (document.body.scrollTop || document.documentElement.scrollTop)
					+ "px",
			left : (k - l) / 2 + "px"
		})
	}
	d.box.appendTo("body");
	d.sucBox.appendTo("body");
	j(d.box);
	j(d.sucBox);
	c()
}();
J.ready(function() {
	J.s(".send-mobile")[0]
			&& J.s(".send-mobile").eq(0).on("click", function(h) {
				h.stop();
				if (J.s(".panel_def").length > 0) {
					J.popup.close()
				}
				if (pro_type == 13) {
					var f = "/v3/ajax/getMsgSentToPhone/", j = {
						send_type : send_type || 0,
						prop_id : prop_info.prop_id || 0
					}, g = "/check/code?cn=haozu&x=120&y=40&s=30&x1=2&y1=46&x2=12&y2=38&t="
				}
				if (pro_type == 2 || pro_type == 3) {
					var f = "/ajax/getMsgSentToPhone/", j = {
						send_type : send_type || 0,
						prop_id : prop_info.prop_id || 0
					}, g = "/check/code?cn=haozu&x=120&y=40&s=30&x1=2&y1=46&x2=12&y2=38&t="
				} else {
					if (pro_type == 1) {
						var f = "/ajax/getToPhoneMsg/", j = {
							prop_id : J.g("send-prop-id").val(),
							prop_type : J.g("prop-type").val()
						}, g = "/seccode?k=seccode&x=101&y=26&s=24&x1=2&y1=30&x2=12&y2=26&t="
					}
				}
				J.get({
							url : f,
							cache : false,
							type : "json",
							timeout : 20000,
							data : j,
							onSuccess : function(k) {
								if (k.status == 0 && J.g("phoneMsg")) {
									J.g("phoneMsg").html("你将会收到如下信息“ " + k.msg
											+ " ”");
									J.g("message").val(k.msg);
									if (pro_type === 1) {
										J.g("message-code").val(k.code)
									}
								}
							}
						});
				J.popup = new J.ui.panel({
							modal : false,
							mask : false,
							close : true,
							width : 579,
							content : J.g("popup-rssnotab").html(),
							onClose : function() {
							}
						});
				J.s(".panel_def").eq(0).setStyle("z-index:999999");
				J.s(".re-code-img").eq(0).attr("src",
						g + (new Date().getTime()));
				J.s(".popup-box .comfirm-cancel").each(function(k, l) {
							l.on("click", function(m) {
										J.popup.close()
									})
						});
				J.s(".popup-box .comfirm").eq(0).on("click", function() {
							h.stop();
							e("phone-num");
							b("re-code");
							if (J.s(".val-wrong").length === 0) {
								a()
							}
						});
				J.s(".re-code-link").eq(0).get().onclick = function() {
					J.s(".re-code-img").eq(0).attr("src",
							g + (new Date().getTime()));
					return false
				}
			});
	function e(l) {
		var h = J.g(l);
		if (!h.length) {
			return
		}
		var g = "phone-info";
		var j = /^[0-9]{11}$/;
		var k = h.val();
		var f = {
			errno : 0,
			msg : "验证成功"
		};
		if (!k) {
			f.errno = 1;
			f.msg = "手机格式错误";
			c(g, f.msg);
			return
		} else {
			if (!j.test(k)) {
				f.errno = 2, f.msg = "手机格式错误";
				c(g, f.msg);
				return
			} else {
				d(g)
			}
		}
	}
	function b(k) {
		var h = J.g(k);
		if (!h.length) {
			return
		}
		var g = "seccode-info";
		var j = h.val();
		var f = {
			errno : 0,
			msg : "验证成功"
		};
		if (!j) {
			f.errno = 1;
			f.msg = "输入错误";
			c(g, f.msg);
			return
		} else {
			if (j.length < 4) {
				f.errno = 2, f.msg = "输入错误";
				c(g, f.msg);
				return
			} else {
				d(g)
			}
		}
	}
	function c(h, g) {
		var f = J.g(h);
		if (!f.length) {
			return
		}
		f.removeClass("val-right");
		f.addClass("val-wrong");
		f.html(g);
		f.show();
		return
	}
	function d(g) {
		var f = J.g(g);
		if (!f.length) {
			return
		}
		f.removeClass("val-wrong");
		f.html("");
		f.show();
		return
	}
	function a() {
		var f = J.g("post-url").val() + "?r=" + Math.random();
		if (pro_type == 1) {
			J.post({
						url : f,
						type : "json",
						async : false,
						data : {
							phone : J.g("phone-num").val(),
							seccode : J.g("re-code").val(),
							message : encodeURIComponent(J.g("message").val()),
							message_code : J.g("message-code").val(),
							from_page : J.g("from-page").val(),
							message_no : encodeURIComponent(J.g("message-no")
									.val())
						},
						onSuccess : function(g) {
							if (g && (g.status === 0 || g.error_code === 3)) {
								J.popup.setContent(J.g("popup-rssok").html());
								J.popup.setAutoClose(3)
							} else {
								if (g && g.status === 1) {
									J.s(".re-code-link")[0].click();
									c("seccode-info", "输入错误")
								} else {
									if (g && g.status === 5) {
										c("phone-info", g.message)
									} else {
										if (g && g.message) {
											J.popup.setContent(J
													.g("popup-rssok").html());
											J.s("#rssok .popup-container")
													.eq(0).html(g.message);
											J.popup.setAutoClose(3)
										} else {
											J.popup.close()
										}
									}
								}
							}
						}
					})
		} else {
			if (pro_type == 2 || pro_type == 3) {
				J.post({
					url : f,
					type : "json",
					async : false,
					data : {
						f : send_type,
						p : prop_info.prop_id,
						m : J.g("phone-num").val(),
						t : "sm",
						c : J.g("re-code").val(),
						v : Math.random(),
						reg_from : "Site_Rent_MP_FP",
						content : encodeURIComponent(J.g("message").val())
					},
					onSuccess : function(h) {
						var g = h;
						if (g == "1") {
							J.popup.setContent(J.g("popup-rssok").html());
							var j = setTimeout("J.popup.close()", 3000)
						} else {
							if (g == "5") {
								c("seccode-info", "验证码错误")
							} else {
								var k = "";
								if (g == "2") {
									k = "您已超过10条短信发送上限"
								} else {
									if (g == "3") {
										k = "您已对本房源发满2条短信"
									} else {
										if (g == "4") {
											k = "两次发送的间隔太短啦，请稍后再试。"
										}
									}
								}
								J.popup.setContent(J.g("popup-rssfail").html());
								if (k) {
									c("mobilesend_fail", k)
								}
								setTimeout("J.popup.close()", 2000)
							}
						}
					}
				})
			}
		}
	}
});
J.ready(function() {
(function() {
		function a(r) {
			var x = {
				id : "",
				onChange : "",
				afterInsert : "",
				items : 5,
				lessItems : "",
				equalItems : "",
				data : [],
				title : "",
				onMouseEnter : "",
				onMouseLeave : "",
				onItemClick : "",
				fixed : false,
				dataTacker : "",
				maxLimit : true
			}, s = {}, q;
			var b = "";
			var H = "";
			var h = "";
			var g, E, B, d, e = 0, t, p;
			var A, C, u, o, v, j;
(function	() {
				s = J.mix(x, r);
				H = '</ul><a onclick="return false;"  class="left" href="javascript:void(0)"/><a class="right"  href="javascript:void(0)" onclick="return false;"></a></div></div></div>';
				b = '<div class="ajax_prop"><div class="hd"><h4>' + s.title
						+ '</h4></div><div class="box"><ul class="cf">';
				t = s.items - 1;
				q = J.g(s.id);
				if (!q || q.length == 0) {
					return
				}
				n();
				if (!s.data.length || s.maxLimit && (s.data.length < s.items)) {
					G();
					return
				}
				var I = "<span class='page' style='display:none'>第1页，共"
						+ Math.ceil(s.data.length / s.items) + "页</span>";
				b = '<div class="ajax_prop"><div class="title"><h4>' + s.title
						+ "</h4>" + I
						+ '</div> <div class="box"><ul class="cf">';
				q.html(b + h + H);
				g = q.s("a").eq(0).get();
				E = q.s("a").eq(1).get();
				j = q.s(".page").eq(0);
				if (s.data.length === s.items) {
					g.style.display = "none";
					E.style.display = "none";
					q.s(".page").eq(0).get().style.display = "none"
				}
				l(g, E);
				f();
				B = q.get().getElementsByTagName("li")
			})();
			function G() {
				s.lessItems && s.lessItems(q);
				n()
			}
			function l(L, I, K) {
				s.equalItems && s.equalItems(L, I);
				d = 0;
				q.s("ul").eq(0)
						.html(D(d, s.maxLimit ? s.items : s.data.length));
				s.afterInsert && s.afterInsert(d, w);
				F()
			}
			function z(L, K) {
				var I = L.ROOMNUM + "室" + L.HALLNUM + "厅，" + L.AREANUM + "平米";
				return (s.onChange && s.onChange(L, K))
						|| '<img class="rec_common_img" title="'
						+ L.TITLE
						+ '" width="180" height="135"  src="'
						+ L.IMAGESRC
						+ '"><a class="rec_common_title" onclick="return false;" data-trace="{viewandview_'
						+ (K + 1) + ":" + L.PROID + '}" title="' + L.TITLE
						+ '"  href="' + L.LINK + "?from=anjuke_page_rec"
						+ L.SOJ + '">' + L.TITLE
						+ '</a><p class="rec_common_price">' + L.PROPRICE
						+ '<span>万</span></p><p class="rec_common_info">' + I
						+ '</p><p class="rec_common_name">' + L.COMMNAME
						+ "</p>"
			}
			function F() {
				q.get().style.display = ""
			}
			function n() {
				q.get().style.display = "none"
			}
			function D(L, M) {
				var K = null, I = "";
				for (; L < M; L++) {
					var K = s.data[L];
					I += '<li class="rec_common_con">' + z(K, L) + "</li>"
				}
				return I
			}
			function c() {
				q.innerHTML = b + h + H;
				F()
			}
			function f() {
				g.onclick = y;
				E.onclick = m;
				var K = J.g(s.id).s("li");
				K.each(function(N, M) {
							M.on("mouseenter", function() {
										M.addClass("hover")
									});
							M.on("mouseleave", function() {
										M.removeClass("hover")
									});
							M.on("click", function() {
										M.s("a").eq(0).get().click()
									});
							M.s("a").each(function(P, O) {
										O.on("click", function(Q) {
													if (Q && Q.stopPropagation) {
														Q.stopPropagation()
													} else {
														window.event.cancelable = true
													}
												})
									})
						});
				if (true || !s.fixed || !q.hasClass("fix_con")) {
					return
				}
				var L = J.s(".img_txt").eq(0);
				var I = J.create("div", {
							style : "float:left;width:1px;height:"
									+ (q.height() + 55) + "px"
						});
				L.append(I);
				v = !-[1,] && !window.XMLHttpRequest;
				J.on(window, "scroll", function() {
							C = q.offset().x;
							A = J.s(".img_txt").eq(0).offset().y;
							o = q.height();
							u = A + J.s(".img_txt").eq(0).height();
							J.ui.timerAni.offsetBotY = u - o;
							J.ui.timerAni.offsetLeft = C;
							J.un(window, "scroll", arguments.callee)
						});
				J.on(window, "resize", function() {
							C = J.s(".img_txt").eq(0).offset().x;
							q.setStyle({
										left : C + "px"
									});
							J.ui.timerAni.offsetLeft = C;
							k()
						});
				J.on(window, "scroll", k)
			}
			function k() {
				var I = J.s(".img_txt").eq(0);
				var L = J.s(".img_txt").eq(0);
				var K = J.page.scrollTop();
				if (K > A) {
					if (K + q.height() + 10 >= u) {
						if (v) {
							q.removeClass("fixed").removeClass("static")
									.addClass("iefixed");
							return
						}
						if (q.get().style.position === "fixed") {
							q.setStyle({
										position : "absolute",
										top : (u - o - 20) + "px"
									})
						}
						return
					}
					v ? (q.removeClass("iefixed").removeClass("static")
							.addClass("fixed"), q.setStyle({
								left : C + "px"
							})) : q.setStyle({
								position : "fixed",
								top : 30 + "px",
								left : C + "px"
							})
				} else {
					v
							&& (q.removeClass("fixed").removeClass("iefixed")
									.addClass("static"));
					q.setStyle({
								position : "",
								top : "",
								left : ""
							})
				}
			}
			function y(N) {
				var M = s.items;
				if (d == 0) {
					d = Math.ceil(s.data.length / M) * M
				}
				g.onclick = null;
				var K = d + e - M;
				var L = s.data[K];
				B[e].style.visibility = L
						? (B[e].innerHTML = (z(L, K)), "visible")
						: (B[e].innerHTML = "", "hidden");
				e++;
				if (e >= M) {
					s.dataTacker && J.site.trackEvent(s.dataTacker);
					g.onclick = y;
					e = 0;
					j.html(j.html().replace(/\d+/, function(O) {
								return Math.ceil(d / s.items)
							}));
					d -= s.items;
					s.afterInsert && s.afterInsert(d, w);
					var I = q.s("li");
					I.each(function(P, O) {
						O.s("a").each(function(R, Q) {
									Q.on("click", function(S) {
												if (S && S.stopPropagation) {
													S.stopPropagation()
												} else {
													window.event.cancelable = true
												}
											})
								})
					});
					return
				}
				p = setTimeout(y, L ? 50 : 0)
			}
			function m(O) {
				var N = s.items;
				var M = Math.ceil(s.data.length / N);
				if (Math.ceil((d + N) / N) >= M) {
					d = -N
				}
				E.onclick = null;
				var K = N + d + t;
				var L = s.data[K];
				B[t].style.visibility = L
						? (B[t].innerHTML = (z(L, K)), "visible")
						: (B[t].innerHTML = "", "hidden");
				t--;
				if (t < 0) {
					s.dataTacker && J.site.trackEvent(s.dataTacker);
					t = N - 1;
					j.html(j.html().replace(/\d+/, function(P) {
								return Math.ceil(d / s.items) + 2
							}));
					d += N;
					E.onclick = m;
					s.afterInsert && s.afterInsert(d, w);
					var I = q.s("li");
					I.each(function(Q, P) {
						P.s("a").each(function(S, R) {
									R.on("click", function(T) {
												if (T && T.stopPropagation) {
													T.stopPropagation()
												} else {
													window.event.cancelable = true
												}
											})
								})
					});
					return
				}
				p = setTimeout(m, L ? 50 : 0)
			}
			function w(P) {
				if (P.propParam == "") {
					return
				}
				var K = new J.logger.Tracker("anjuke-npv");
				K.setPage("View_Property_ViewPropRecommend");
				K.setPageName("Anjuke_Hp_View");
				K.setReferrer(document.referrer);
				K.setNGuid("aQQ_ajkguid");
				K.setNUid("ajk_member_id");
				try {
					var L = J.getCookie("ajk_member_id") || 0;
					var O = J.getCookie("usertype") || 0;
					var N = P.entry ? P.entry : 2;
					var I = '"entry":"' + N + '","proids":"' + P.propParam
							+ '","tradeType":"1"';
					cp = '{"v":"'
							+ P.v
							+ '","hp":"'
							+ P.hp
							+ '","hpPage":"Anjuke_Hp_View","channel":"1","userType":"'
							+ O + '","userid":"' + L + '",' + I
							+ ',"recomm_data_type":"' + P.recomm_data_type
							+ '","recomm_type":"' + P.recomm_type + '"}';
					K.setCustomParam(cp);
					K.track()
				} catch (M) {
					console.log(M)
				}
			}
		}
		J.ui.timerAni = a
	})();
(function() {
		function b() {
			if (J.g("propSimilarBox") && J.g("recommendNear")) {
				var f = {
					city_id : city_id,
					prop_id : prop_info.prop_id,
					guid : userId,
					prop_price : prop_info.pro_price,
					num : 5,
					show_soj : 1,
					model_type : 1,
					source_type : prop_info.source_type,
					from : "site_sale_page"
				}, e = this;
				J.get({
					timeout : 20000,
					url : "/v3/ajax/ershouviewrecommend/",
					type : "json",
					data : f,
					onSuccess : function(j) {
						if (j && (j.status == "ok")) {
							j = a(j);
							var n = j.is_main_push;
							var h = (n == 1)
									? j.main_push_props
									: j.similar_comm_props, m = (n == 1)
									? j.soj_data_main_push
									: j.soj_data_similar_comm;
							m.propParam = m.propParam ? m.propParam : "";
							var g = m.propParam.split(",");
							var k = {};
							var l = "anjuke_page_rec_right";
							J.ui.timerAni && (new J.ui.timerAni({
								id : "propSimilarBox",
								onChange : function(s, r) {
									var o = s.room_num + "室" + s.hall_num
											+ "厅，" + s.area_num + "平米";
									if (n == 1) {
										var p = s.soj.replace("proprec",
												"mainpush");
										var q = '<i class="rec_icon"></i><img class="rec_common_img" title="'
												+ s.title
												+ '" width="180" height="135" src="'
												+ s.default_image
												+ '"><a target="_blank" data-sign="true"  data-trace="{viewandview_'
												+ (r + 1)
												+ ":"
												+ s.prop_id
												+ '}" title="'
												+ s.title
												+ '" class="rec_common_title" href="'
												+ s.prop_url
												+ "?from="
												+ l
												+ p
												+ '">'
												+ s.title
												+ '</a><p class="rec_common_price">'
												+ s.prop_price
												+ '<span>万</span></p><p class="rec_common_info">'
												+ o
												+ '</p><p class="rec_common_name">'
												+ s.comm_name + "</p>"
									} else {
										if (n == 0) {
											var p = s.soj.replace("proprec",
													"scanagain");
											var q = '<img class="rec_common_img" title="'
													+ s.title
													+ '" width="180" height="135" src="'
													+ s.default_image
													+ '"><a target="_blank" data-sign="true"  data-trace="{viewandview_'
													+ (r + 1)
													+ ":"
													+ s.prop_id
													+ '}" title="'
													+ s.title
													+ '" class="rec_common_title" href="'
													+ s.prop_url
													+ "?from="
													+ l
													+ p
													+ '">'
													+ s.title
													+ '</a><p class="rec_common_price">'
													+ s.prop_price
													+ '<span>万</span></p><p class="rec_common_info">'
													+ o
													+ '</p><p class="rec_common_name">'
													+ s.comm_name + "</p>"
										}
									}
									return q
								},
								afterInsert : function(o, q) {
									if (!k[o]) {
										var p = g.slice(o, g.length);
										m.propParam = p.join(",");
										q(m);
										k[o] = true
									}
								},
								maxLimit : false,
								items : 5,
								data : h,
								title : (n == 1) ? J.g("broker_true_name")
										&& J.g("broker_true_name").html()
										+ "的主推房源" : "相似小区房源",
								fixed : true,
								dataTacker : "look_and_look_prop"
							}))
						}
					}
				});
				J.get({
					timeout : 20000,
					url : "/v3/ajax/ershouview/interestrecommend/",
					type : "json",
					data : f,
					onSuccess : function(g) {
						if (g && (g.status == "ok")) {
							J.ui.timerAni && (new J.ui.timerAni({
								id : "recommendNear",
								onChange : function(k, j) {
									var h = k.room_num + "室" + k.hall_num
											+ "厅，" + k.area_num + "平米";
									return '<img class="rec_common_img" title="'
											+ k.title
											+ '" width="180" height="135" src="'
											+ k.default_image
											+ '"><a target="_blank" data-sign="true"  data-trace="{viewandview_'
											+ (j + 1)
											+ ":"
											+ k.prop_id
											+ '}" title="'
											+ k.title
											+ '" class="rec_common_title" href="'
											+ k.prop_url
											+ "?from=anjuke_page_rec_cnxh"
											+ k.soj
											+ '">'
											+ k.title
											+ '</a><p class="rec_common_price">'
											+ k.prop_price
											+ '<span>万</span></p><p class="rec_common_info">'
											+ h
											+ '</p><p class="rec_common_name">'
											+ k.comm_name + "</p>"
								},
								afterInsert : function(h, j) {
									j(g.soj_data_interest)
								},
								items : 4,
								data : g.interest_props,
								title : "猜你喜欢",
								dataTacker : "near_comm_prop"
							}))
						}
					}
				})
			}
		}
		function c() {
			if (J.g("propSimilarBox") && J.g("recommendNear")) {
				var f = {
					city_id : city_id,
					prop_id : prop_info.prop_id,
					guid : userId,
					prop_price : prop_info.pro_price,
					num : 4,
					show_soj : 1,
					source_type : prop_info.source_type,
					model_type : 2,
					block_id : block_id,
					from : "site_sale_page"
				}, e = this;
				J.get({
					timeout : 20000,
					url : "/v3/ajax/ershouviewrecommend/",
					type : "json",
					data : f,
					onSuccess : function(g) {
						if (g && (g.status == "ok")) {
							J.ui.timerAni && (new J.ui.timerAni({
								id : "blockRecommendProp",
								onChange : function(k, j) {
									var h = k.room_num + "室" + k.hall_num
											+ "厅，" + k.area_num + "平米";
									return '<img class="rec_common_img" title="'
											+ k.title
											+ '" width="180" height="135" src="'
											+ k.default_image
											+ '"><a target="_blank" data-sign="true"  data-trace="{viewandview_'
											+ (j + 1)
											+ ":"
											+ k.prop_id
											+ '}" title="'
											+ k.title
											+ '" class="rec_common_title" href="'
											+ k.prop_url
											+ "?from=anjuke_page_rec_tddyx"
											+ k.soj
											+ '">'
											+ k.title
											+ '</a><p class="rec_common_price">'
											+ k.prop_price
											+ '<span>万</span></p><p class="rec_common_info">'
											+ h
											+ '</p><p class="rec_common_name">'
											+ k.comm_name + "</p>"
								},
								afterInsert : function(h, j) {
									j(g.soj_block_recommend_prop)
								},
								items : 4,
								data : g.block_recommend_prop,
								title : "同地段优选",
								dataTacker : "block_recommend_prop"
							}));
							J.ui.timerAni && (new J.ui.timerAni({
								id : "areaPriceRecommendProp",
								onChange : function(k, j) {
									var h = k.room_num + "室" + k.hall_num
											+ "厅，" + k.area_num + "平米";
									return '<img class="rec_common_img" title="'
											+ k.title
											+ '" width="180" height="135" src="'
											+ k.default_image
											+ '"><a target="_blank" data-sign="true"  data-trace="{viewandview_'
											+ (j + 1)
											+ ":"
											+ k.prop_id
											+ '}" title="'
											+ k.title
											+ '" class="rec_common_title" href="'
											+ k.prop_url
											+ "?from=anjuke_page_rec_tygyzf"
											+ k.soj
											+ '">'
											+ k.title
											+ '</a><p class="rec_common_price">'
											+ k.prop_price
											+ '<span>万</span></p><p class="rec_common_info">'
											+ h
											+ '</p><p class="rec_common_name">'
											+ k.comm_name + "</p>"
								},
								afterInsert : function(h, j) {
									j(g.soj_area_price_recommend_prop)
								},
								items : 4,
								data : g.area_price_recommend_prop,
								title : "同月供优质房",
								dataTacker : "area_price_recommend_prop"
							}));
							J.ui.timerAni && (new J.ui.timerAni({
								id : "potentialRecommendProp",
								onChange : function(k, j) {
									var h = k.room_num + "室" + k.hall_num
											+ "厅，" + k.area_num + "平米";
									return '<img class="rec_common_img" title="'
											+ k.title
											+ '" width="180" height="135" src="'
											+ k.default_image
											+ '"><a target="_blank" data-sign="true"  data-trace="{viewandview_'
											+ (j + 1)
											+ ":"
											+ k.prop_id
											+ '}" title="'
											+ k.title
											+ '" class="rec_common_title" href="'
											+ k.prop_url
											+ "?from=anjuke_page_rec_tzqlf"
											+ k.soj
											+ '">'
											+ k.title
											+ '</a><p class="rec_common_price">'
											+ k.prop_price
											+ '<span>万</span></p><p class="rec_common_info">'
											+ h
											+ '</p><p class="rec_common_name">'
											+ k.comm_name + "</p>"
								},
								afterInsert : function(h, j) {
									j(g.soj_potential_recommend_prop)
								},
								items : 4,
								data : g.potential_recommend_prop,
								title : "投资潜力房",
								dataTacker : "potential_recommend_prop"
							}))
						}
					}
				})
			}
		}
		function a(e) {
			if (e.interest_props && e.interest_props.length > 0) {
				J.site
						.trackEvent("exp_View_Property_ViewPropSalePage_recommend_view_left")
			}
			if (e.similar_comm_props && e.similar_comm_props.length <= 5) {
				J.site
						.trackEvent("exp_View_Property_ViewPropSalePage_recommend_view_right")
			}
			return e
		}
		b();
		c();
		function d(f) {
			var e = J.g("propWubaBox").s(".rec_common_con");
			e.each(function(h, g) {
						g.on("click", function() {
									var p = f[h];
									if (p.cp == "") {
										return
									}
									var k = new J.logger.Tracker("anjuke-npv");
									k.setPage("esf_pro_jxesf");
									k.setPageName("esf_pro_jxesf");
									k.setReferrer(document.referrer);
									k.setNGuid("aQQ_ajkguid");
									k.setNUid("ajk_member_id");
									try {
										var l = J.getCookie("ajk_member_id")
												|| 0;
										var o = J.getCookie("usertype") || 0;
										var n = p.entry ? p.entry : 2;
										var j = '"entry":"' + n
												+ '","proids":"' + p.prop_id
												+ '","tradeType":"1"';
										cp = '{"v":"' + p.cp.v
												+ '","channel":"'
												+ p.cp.channel + '","userId":"'
												+ p.cp.userId
												+ '","userType":"'
												+ p.cp.userType
												+ '","tradeType":"'
												+ p.cp.tradeType
												+ '","proId":"' + p.cp.proId
												+ '","COMMID":"' + p.cp.COMMID
												+ '","brokerId":"'
												+ p.cp.brokerId
												+ '","brokerType":"'
												+ p.cp.brokerType
												+ '","hpType":"' + p.cp.hpType
												+ '","entry":"' + p.cp.entry
												+ '","uniqid":"' + p.cp.uniqid
												+ '","romar_item":"'
												+ p.cp.romar_item
												+ '","userProId":"'
												+ p.cp.userProId + '"}';
										k.setCustomParam(cp);
										k.track()
									} catch (m) {
										console.log(m)
									}
								})
					})
		}
	})()
});
(function(j) {
	var e = "0.4.2", k = "hasOwnProperty", b = /[\.\/]/, a = "*", g = function() {
	}, f = function(n, m) {
		return n - m
	}, d, h, l = {
		n : {}
	}, c = function(m, C) {
		m = String(m);
		var v = l, s = h, w = Array.prototype.slice.call(arguments, 2), y = c
				.listeners(m), x = 0, u = false, p, o = [], t = {}, q = [], n = d, A = [];
		d = m;
		h = 0;
		for (var r = 0, B = y.length; r < B; r++) {
			if ("zIndex" in y[r]) {
				o.push(y[r].zIndex);
				if (y[r].zIndex < 0) {
					t[y[r].zIndex] = y[r]
				}
			}
		}
		o.sort(f);
		while (o[x] < 0) {
			p = t[o[x++]];
			q.push(p.apply(C, w));
			if (h) {
				h = s;
				return q
			}
		}
		for (r = 0; r < B; r++) {
			p = y[r];
			if ("zIndex" in p) {
				if (p.zIndex == o[x]) {
					q.push(p.apply(C, w));
					if (h) {
						break
					}
					do {
						x++;
						p = t[o[x]];
						p && q.push(p.apply(C, w));
						if (h) {
							break
						}
					} while (p)
				} else {
					t[p.zIndex] = p
				}
			} else {
				q.push(p.apply(C, w));
				if (h) {
					break
				}
			}
		}
		h = s;
		d = n;
		return q.length ? q : null
	};
	c._events = l;
	c.listeners = function(m) {
		var u = m.split(b), s = l, y, t, n, q, x, p, r, v, w = [s], o = [];
		for (q = 0, x = u.length; q < x; q++) {
			v = [];
			for (p = 0, r = w.length; p < r; p++) {
				s = w[p].n;
				t = [s[u[q]], s[a]];
				n = 2;
				while (n--) {
					y = t[n];
					if (y) {
						v.push(y);
						o = o.concat(y.f || [])
					}
				}
			}
			w = v
		}
		return o
	};
	c.on = function(m, p) {
		m = String(m);
		if (typeof p != "function") {
			return function() {
			}
		}
		var r = m.split(b), q = l;
		for (var n = 0, o = r.length; n < o; n++) {
			q = q.n;
			q = q.hasOwnProperty(r[n]) && q[r[n]] || (q[r[n]] = {
				n : {}
			})
		}
		q.f = q.f || [];
		for (n = 0, o = q.f.length; n < o; n++) {
			if (q.f[n] == p) {
				return g
			}
		}
		q.f.push(p);
		return function(s) {
			if (+s == +s) {
				p.zIndex = +s
			}
		}
	};
	c.f = function(n) {
		var m = [].slice.call(arguments, 1);
		return function() {
			c.apply(null, [n, null].concat(m).concat([].slice
							.call(arguments, 0)))
		}
	};
	c.stop = function() {
		h = 1
	};
	c.nt = function(m) {
		if (m) {
			return new RegExp("(?:\\.|\\/|^)" + m + "(?:\\.|\\/|$)").test(d)
		}
		return d
	};
	c.nts = function() {
		return d.split(b)
	};
	c.off = c.unbind = function(n, s) {
		if (!n) {
			c._events = l = {
				n : {}
			};
			return
		}
		var u = n.split(b), t, w, o, q, x, p, r, v = [l];
		for (q = 0, x = u.length; q < x; q++) {
			for (p = 0; p < v.length; p += o.length - 2) {
				o = [p, 1];
				t = v[p].n;
				if (u[q] != a) {
					if (t[u[q]]) {
						o.push(t[u[q]])
					}
				} else {
					for (w in t) {
						if (t[k](w)) {
							o.push(t[w])
						}
					}
				}
				v.splice.apply(v, o)
			}
		}
		for (q = 0, x = v.length; q < x; q++) {
			t = v[q];
			while (t.n) {
				if (s) {
					if (t.f) {
						for (p = 0, r = t.f.length; p < r; p++) {
							if (t.f[p] == s) {
								t.f.splice(p, 1);
								break
							}
						}
						!t.f.length && delete t.f
					}
					for (w in t.n) {
						if (t.n[k](w) && t.n[w].f) {
							var m = t.n[w].f;
							for (p = 0, r = m.length; p < r; p++) {
								if (m[p] == s) {
									m.splice(p, 1);
									break
								}
							}
							!m.length && delete t.n[w].f
						}
					}
				} else {
					delete t.f;
					for (w in t.n) {
						if (t.n[k](w) && t.n[w].f) {
							delete t.n[w].f
						}
					}
				}
				t = t.n
			}
		}
	};
	c.version = e;
	c.toString = function() {
		return "You are running Eve " + e
	};
	(typeof module != "undefined" && module.exports)
			? (module.exports = c)
			: (typeof define != "undefined" ? (define("eve", [], function() {
						return c
					})) : (j.eve = c))
})(window || this);
(function(b, a) {
	if (typeof define === "function" && define.amd) {
		define(["eve"], function(c) {
					return a(b, c)
				})
	} else {
		a(b, b.eve)
	}
}(this, function(be, u) {
	function aG(g) {
		if (aG.is(g, "function")) {
			return af ? g() : u.on("drag.DOMload", g)
		} else {
			if (aG.is(g, aW)) {
				return aG._engine.create[bq](aG,
						g.splice(0, 3 + aG.is(g[0], aA))).add(g)
			} else {
				var b = Array.prototype.slice.call(arguments, 0);
				if (aG.is(b[b.length - 1], "function")) {
					var d = b.pop();
					return af ? d.call(aG._engine.create[bq](aG, b)) : u.on(
							"drag.DOMload", function() {
								d.call(aG._engine.create[bq](aG, b))
							})
				} else {
					return aG._engine.create[bq](aG, arguments)
				}
			}
		}
	}
	aG.version = "2.1.2";
	aG.eve = u;
	var af, a = /[, ]+/, bf = {
		circle : 1,
		rect : 1,
		path : 1,
		ellipse : 1,
		text : 1,
		image : 1
	}, a9 = /\{(\d+)\}/g, bt = "prototype", ab = "hasOwnProperty", ao = {
		doc : document,
		win : be
	}, q = {
		was : Object.prototype[ab].call(ao.win, "Drag"),
		is : ao.win.Drag
	}, bo = function() {
		this.ca = this.customAttributes = {}
	}, aR, a6 = "appendChild", bq = "apply", bn = "concat", T = ("ontouchstart" in ao.win)
			|| ao.win.DocumentTouch && ao.doc instanceof DocumentTouch, aL = "", aF = " ", br = String, z = "split", K = "click dblclick mousedown mousemove mouseout mouseover mouseup touchstart touchmove touchend touchcancel"[z](aF), bg = {
		mousedown : "touchstart",
		mousemove : "touchmove",
		mouseup : "touchend"
	}, bu = br.prototype.toLowerCase, aj = Math, l = aj.max, a4 = aj.min, al = aj.abs, a7 = aj.pow, aJ = aj.PI, aA = "number", aa = "string", aW = "array", aS = "toString", aU = "fill", aP = Object.prototype.toString, bi = {}, j = "push", f = aG._ISURL = /^url\(['"]?([^\)]+?)['"]?\)$/i, x = /^\s*((#[a-f\d]{6})|(#[a-f\d]{3})|rgba?\(\s*([\d\.]+%?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+%?(?:\s*,\s*[\d\.]+%?)?)\s*\)|hsba?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+(?:%?\s*,\s*[\d\.]+)?)%?\s*\)|hsla?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+(?:%?\s*,\s*[\d\.]+)?)%?\s*\))\s*$/i, ak = {
		"NaN" : 1,
		"Infinity" : 1,
		"-Infinity" : 1
	}, c = /^(?:cubic-)?bezier\(([^,]+),([^,]+),([^,]+),([^\)]+)\)/, Y = aj.round, w = "setAttribute", ae = parseFloat, M = parseInt, bb = br.prototype.toUpperCase, p = aG._availableAttrs = {
		"arrow-end" : "none",
		"arrow-start" : "none",
		blur : 0,
		"clip-rect" : "0 0 1e9 1e9",
		cursor : "default",
		cx : 0,
		cy : 0,
		fill : "#fff",
		"fill-opacity" : 1,
		font : '10px "Arial"',
		"font-family" : '"Arial"',
		"font-size" : "10",
		"font-style" : "normal",
		"font-weight" : 400,
		gradient : 0,
		height : 0,
		href : "http://smallmo.com/",
		"letter-spacing" : 0,
		opacity : 1,
		path : "M0,0",
		r : 0,
		rx : 0,
		ry : 0,
		src : "",
		stroke : "#000",
		"stroke-dasharray" : "",
		"stroke-linecap" : "butt",
		"stroke-linejoin" : "butt",
		"stroke-miterlimit" : 0,
		"stroke-opacity" : 1,
		"stroke-width" : 1,
		target : "_blank",
		"text-anchor" : "middle",
		title : "Drag",
		transform : "",
		width : 0,
		x : 0,
		y : 0
	}, ai = aG._availableAnimAttrs = {
		blur : aA,
		"clip-rect" : "csv",
		cx : aA,
		cy : aA,
		fill : "colour",
		"fill-opacity" : aA,
		"font-size" : aA,
		height : aA,
		opacity : aA,
		path : "path",
		r : aA,
		rx : aA,
		ry : aA,
		stroke : "colour",
		"stroke-opacity" : aA,
		"stroke-width" : aA,
		transform : "transform",
		width : aA,
		x : aA,
		y : aA
	}, W = /[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]/g, a1 = /[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*/, m = {
		hs : 1,
		rg : 1
	}, aZ = /,?([achlmqrstvxz]),?/gi, aO = /([achlmrqstvz])[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029,]*((-?\d*\.?\d*(?:e[\-+]?\d+)?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*)+)/ig, Z = /([rstm])[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029,]*((-?\d*\.?\d*(?:e[\-+]?\d+)?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*)+)/ig, aE = /(-?\d*\.?\d*(?:e[\-+]?\d+)?)[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*/ig, aK = aG._radial_gradient = /^r(?:\(([^,]+?)[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*([^\)]+?)\))?/, aI = {}, a8 = function(
			g, d) {
		return g.key - d.key
	}, r = function(g, d) {
		return ae(g) - ae(d)
	}, C = function() {
	}, bk = function(b) {
		return b
	}, an = aG._rectPath = function(b, R, d, g, E) {
		if (E) {
			return [["M", b + E, R], ["l", d - E * 2, 0],
					["a", E, E, 0, 0, 1, E, E], ["l", 0, g - E * 2],
					["a", E, E, 0, 0, 1, -E, E], ["l", E * 2 - d, 0],
					["a", E, E, 0, 0, 1, -E, -E], ["l", 0, E * 2 - g],
					["a", E, E, 0, 0, 1, E, -E], ["z"]]
		}
		return [["M", b, R], ["l", d, 0], ["l", 0, g], ["l", -d, 0], ["z"]]
	}, D = function(b, E, g, d) {
		if (d == null) {
			d = g
		}
		return [["M", b, E], ["m", 0, -d], ["a", g, d, 0, 1, 1, 0, 2 * d],
				["a", g, d, 0, 1, 1, 0, -2 * d], ["z"]]
	}, H = aG._getPath = {
		path : function(b) {
			return b.attr("path")
		},
		circle : function(d) {
			var b = d.attrs;
			return D(b.cx, b.cy, b.r)
		},
		ellipse : function(d) {
			var b = d.attrs;
			return D(b.cx, b.cy, b.rx, b.ry)
		},
		rect : function(d) {
			var b = d.attrs;
			return an(b.x, b.y, b.width, b.height, b.r)
		},
		image : function(d) {
			var b = d.attrs;
			return an(b.x, b.y, b.width, b.height)
		},
		text : function(b) {
			var d = b._getBBox();
			return an(d.x, d.y, d.width, d.height)
		},
		set : function(b) {
			var d = b._getBBox();
			return an(d.x, d.y, d.width, d.height)
		}
	}, F = aG.mapPath = function(bx, S) {
		if (!S) {
			return bx
		}
		var bv, R, g, b, bw, E, d;
		bx = O(bx);
		for (g = 0, bw = bx.length; g < bw; g++) {
			d = bx[g];
			for (b = 1, E = d.length; b < E; b += 2) {
				bv = S.x(d[b], d[b + 1]);
				R = S.y(d[b], d[b + 1]);
				d[b] = bv;
				d[b + 1] = R
			}
		}
		return bx
	};
	aG._g = ao;
	aG.type = (ao.win.SVGAngle
			|| ao.doc.implementation.hasFeature(
					"http://www.w3.org/TR/SVG11/feature#BasicStructure", "1.1")
			? "SVG"
			: "VML");
	if (aG.type == "VML") {
		var at = ao.doc.createElement("div"), ax;
		at.innerHTML = '<v:shape adj="1"/>';
		ax = at.firstChild;
		ax.style.behavior = "url(#default#VML)";
		if (!(ax && typeof ax.adj == "object")) {
			return (aG.type = aL)
		}
		at = null
	}
	aG.svg = !(aG.vml = aG.type == "VML");
	aG._Paper = bo;
	aG.fn = aR = bo.prototype = aG.prototype;
	aG._id = 0;
	aG._oid = 0;
	aG.is = function(d, b) {
		b = bu.call(b);
		if (b == "finite") {
			return !ak[ab](+d)
		}
		if (b == "array") {
			return d instanceof Array
		}
		return (b == "null" && d === null) || (b == typeof d && d !== null)
				|| (b == "object" && d === Object(d))
				|| (b == "array" && Array.isArray && Array.isArray(d))
				|| aP.call(d).slice(8, -1).toLowerCase() == b
	};
	function P(g) {
		if (typeof g == "function" || Object(g) !== g) {
			return g
		}
		var d = new g.constructor;
		for (var b in g) {
			if (g[ab](b)) {
				d[b] = P(g[b])
			}
		}
		return d
	}
	aG.angle = function(R, bv, g, S, d, E) {
		if (d == null) {
			var b = R - g, bw = bv - S;
			if (!b && !bw) {
				return 0
			}
			return (180 + aj.atan2(-bw, -b) * 180 / aJ + 360) % 360
		} else {
			return aG.angle(R, bv, d, E) - aG.angle(g, S, d, E)
		}
	};
	aG.rad = function(b) {
		return b % 360 * aJ / 180
	};
	aG.deg = function(b) {
		return b * 180 / aJ % 360
	};
	aG.snapTo = function(d, E, b) {
		b = aG.is(b, "finite") ? b : 10;
		if (aG.is(d, aW)) {
			var g = d.length;
			while (g--) {
				if (al(d[g] - E) <= b) {
					return d[g]
				}
			}
		} else {
			d = +d;
			var R = E % d;
			if (R < b) {
				return E - R
			}
			if (R > d - b) {
				return E - R + d
			}
		}
		return E
	};
	var h = aG.createUUID = (function(b, d) {
		return function() {
			return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(b, d)
					.toUpperCase()
		}
	})(/[xy]/g, function(g) {
				var d = aj.random() * 16 | 0, b = g == "x" ? d : (d & 3 | 8);
				return b.toString(16)
			});
	aG.setWindow = function(b) {
		u("drag.setWindow", aG, ao.win, b);
		ao.win = b;
		ao.doc = ao.win.document;
		if (aG._engine.initWin) {
			aG._engine.initWin(ao.win)
		}
	};
	var aY = function(g) {
		if (aG.vml) {
			var b = /^\s+|\s+$/g;
			var R;
			try {
				var S = new ActiveXObject("htmlfile");
				S.write("<body>");
				S.close();
				R = S.body
			} catch (bv) {
				R = createPopup().document.body
			}
			var d = R.createTextRange();
			aY = av(function(bw) {
						try {
							R.style.color = br(bw).replace(b, aL);
							var bx = d.queryCommandValue("ForeColor");
							bx = ((bx & 255) << 16) | (bx & 65280)
									| ((bx & 16711680) >>> 16);
							return "#" + ("000000" + bx.toString(16)).slice(-6)
						} catch (by) {
							return "none"
						}
					})
		} else {
			var E = ao.doc.createElement("i");
			E.title = "Rapha\xebl Colour Picker";
			E.style.display = "none";
			ao.doc.body.appendChild(E);
			aY = av(function(bw) {
						E.style.color = bw;
						return ao.doc.defaultView.getComputedStyle(E, aL)
								.getPropertyValue("color")
					})
		}
		return aY(g)
	}, aw = function() {
		return "hsb(" + [this.h, this.s, this.b] + ")"
	}, G = function() {
		return "hsl(" + [this.h, this.s, this.l] + ")"
	}, v = function() {
		return this.hex
	}, aM = function(S, R, d) {
		if (R == null && aG.is(S, "object") && "r" in S && "g" in S && "b" in S) {
			d = S.b;
			R = S.g;
			S = S.r
		}
		if (R == null && aG.is(S, aa)) {
			var E = aG.getRGB(S);
			S = E.r;
			R = E.g;
			d = E.b
		}
		if (S > 1 || R > 1 || d > 1) {
			S /= 255;
			R /= 255;
			d /= 255
		}
		return [S, R, d]
	}, aQ = function(S, R, d, bv) {
		S *= 255;
		R *= 255;
		d *= 255;
		var E = {
			r : S,
			g : R,
			b : d,
			hex : aG.rgb(S, R, d),
			toString : v
		};
		aG.is(bv, "finite") && (E.opacity = bv);
		return E
	};
	aG._path2string = function() {
		return this.join(",").replace(aZ, "$1")
	};
	function a2(E, g) {
		for (var b = 0, d = E.length; b < d; b++) {
			if (E[b] === g) {
				return E.push(E.splice(b, 1)[0])
			}
		}
	}
	function av(E, d, b) {
		function g() {
			var R = Array.prototype.slice.call(arguments, 0), bv = R
					.join("\u2400"), S = g.cache = g.cache || {}, bw = g.count = g.count
					|| [];
			if (S[ab](bv)) {
				a2(bw, bv);
				return b ? b(S[bv]) : S[bv]
			}
			bw.length >= 1000 && delete S[bw.shift()];
			bw.push(bv);
			S[bv] = E[bq](d, R);
			return b ? b(S[bv]) : S[bv]
		}
		return g
	}
	var bd = aG._preload = function(g, d) {
		var b = ao.doc.createElement("img");
		b.style.cssText = "position:absolute;left:-9999em;top:-9999em";
		b.onload = function() {
			d.call(this);
			this.onload = null;
			ao.doc.body.removeChild(this)
		};
		b.onerror = function() {
			ao.doc.body.removeChild(this)
		};
		ao.doc.body.appendChild(b);
		b.src = g
	};
	function ah() {
		return this.hex
	}
	aG.getRGB = av(function(b) {
				if (!b || !!((b = br(b)).indexOf("-") + 1)) {
					return {
						r : -1,
						g : -1,
						b : -1,
						hex : "none",
						error : 1,
						toString : ah
					}
				}
				if (b == "none") {
					return {
						r : -1,
						g : -1,
						b : -1,
						hex : "none",
						toString : ah
					}
				}
				!(m[ab](b.toLowerCase().substring(0, 2)) || b.charAt() == "#")
						&& (b = aY(b));
				var R, d, g, bv, E, bx, bw, S = b.match(x);
				if (S) {
					if (S[2]) {
						bv = M(S[2].substring(5), 16);
						g = M(S[2].substring(3, 5), 16);
						d = M(S[2].substring(1, 3), 16)
					}
					if (S[3]) {
						bv = M((bx = S[3].charAt(3)) + bx, 16);
						g = M((bx = S[3].charAt(2)) + bx, 16);
						d = M((bx = S[3].charAt(1)) + bx, 16)
					}
					if (S[4]) {
						bw = S[4][z](a1);
						d = ae(bw[0]);
						bw[0].slice(-1) == "%" && (d *= 2.55);
						g = ae(bw[1]);
						bw[1].slice(-1) == "%" && (g *= 2.55);
						bv = ae(bw[2]);
						bw[2].slice(-1) == "%" && (bv *= 2.55);
						S[1].toLowerCase().slice(0, 4) == "rgba"
								&& (E = ae(bw[3]));
						bw[3] && bw[3].slice(-1) == "%" && (E /= 100)
					}
					if (S[5]) {
						bw = S[5][z](a1);
						d = ae(bw[0]);
						bw[0].slice(-1) == "%" && (d *= 2.55);
						g = ae(bw[1]);
						bw[1].slice(-1) == "%" && (g *= 2.55);
						bv = ae(bw[2]);
						bw[2].slice(-1) == "%" && (bv *= 2.55);
						(bw[0].slice(-3) == "deg" || bw[0].slice(-1) == "\xb0")
								&& (d /= 360);
						S[1].toLowerCase().slice(0, 4) == "hsba"
								&& (E = ae(bw[3]));
						bw[3] && bw[3].slice(-1) == "%" && (E /= 100);
						return aG.hsb2rgb(d, g, bv, E)
					}
					if (S[6]) {
						bw = S[6][z](a1);
						d = ae(bw[0]);
						bw[0].slice(-1) == "%" && (d *= 2.55);
						g = ae(bw[1]);
						bw[1].slice(-1) == "%" && (g *= 2.55);
						bv = ae(bw[2]);
						bw[2].slice(-1) == "%" && (bv *= 2.55);
						(bw[0].slice(-3) == "deg" || bw[0].slice(-1) == "\xb0")
								&& (d /= 360);
						S[1].toLowerCase().slice(0, 4) == "hsla"
								&& (E = ae(bw[3]));
						bw[3] && bw[3].slice(-1) == "%" && (E /= 100);
						return aG.hsl2rgb(d, g, bv, E)
					}
					S = {
						r : d,
						g : g,
						b : bv,
						toString : ah
					};
					S.hex = "#"
							+ (16777216 | bv | (g << 8) | (d << 16))
									.toString(16).slice(1);
					aG.is(E, "finite") && (S.opacity = E);
					return S
				}
				return {
					r : -1,
					g : -1,
					b : -1,
					hex : "none",
					error : 1,
					toString : ah
				}
			}, aG);
	aG.hsb = av(function(E, g, d) {
				return aG.hsb2rgb(E, g, d).hex
			});
	aG.hsl = av(function(g, d, b) {
				return aG.hsl2rgb(g, d, b).hex
			});
	aG.rgb = av(function(R, E, d) {
				return "#"
						+ (16777216 | d | (E << 8) | (R << 16)).toString(16)
								.slice(1)
			});
	aG.getColor = function(d) {
		var g = this.getColor.start = this.getColor.start || {
			h : 0,
			s : 1,
			b : d || 0.75
		}, b = this.hsb2rgb(g.h, g.s, g.b);
		g.h += 0.075;
		if (g.h > 1) {
			g.h = 0;
			g.s -= 0.2;
			g.s <= 0 && (this.getColor.start = {
				h : 0,
				s : 1,
				b : g.b
			})
		}
		return b.hex
	};
	aG.getColor.reset = function() {
		delete this.start
	};
	aG.parsePathString = function(b) {
		if (!b) {
			return null
		}
		var g = Q(b);
		if (g.arr) {
			return aN(g.arr)
		}
		var E = {
			a : 7,
			c : 6,
			h : 1,
			l : 2,
			m : 2,
			r : 4,
			q : 4,
			s : 4,
			t : 2,
			v : 1,
			z : 0
		}, d = [];
		if (aG.is(b, aW) && aG.is(b[0], aW)) {
			d = aN(b)
		}
		if (!d.length) {
			br(b).replace(aO, function(S, R, bx) {
						var bw = [], bv = R.toLowerCase();
						bx.replace(aE, function(bz, by) {
									by && bw.push(+by)
								});
						if (bv == "m" && bw.length > 2) {
							d.push([R][bn](bw.splice(0, 2)));
							bv = "l";
							R = R == "m" ? "l" : "L"
						}
						if (bv == "r") {
							d.push([R][bn](bw))
						} else {
							while (bw.length >= E[bv]) {
								d.push([R][bn](bw.splice(0, E[bv])));
								if (!E[bv]) {
									break
								}
							}
						}
					})
		}
		d.toString = aG._path2string;
		g.arr = aN(d);
		return d
	};
	aG.parseTransformString = av(function(d) {
				if (!d) {
					return null
				}
				var g = {
					r : 3,
					s : 4,
					t : 2,
					m : 6
				}, b = [];
				if (aG.is(d, aW) && aG.is(d[0], aW)) {
					b = aN(d)
				}
				if (!b.length) {
					br(d).replace(Z, function(R, E, bw) {
								var bv = [], S = bu.call(E);
								bw.replace(aE, function(by, bx) {
											bx && bv.push(+bx)
										});
								b.push([E][bn](bv))
							})
				}
				b.toString = aG._path2string;
				return b
			});
	var Q = function(d) {
		var b = Q.ps = Q.ps || {};
		if (b[d]) {
			b[d].sleep = 100
		} else {
			b[d] = {
				sleep : 100
			}
		}
		setTimeout(function() {
					for (var g in b) {
						if (b[ab](g) && g != d) {
							b[g].sleep--;
							!b[g].sleep && delete b[g]
						}
					}
				});
		return b[d]
	};
	aG._removedFactory = function(b) {
		return function() {
			u("raphael.log", null,
					"Rapha\xebl: you are calling to method \u201c" + b
							+ "\u201d of removed object", b)
		}
	};
	var ad = aG.pathBBox = function(bF) {
		var by = Q(bF);
		if (by.bbox) {
			return P(by.bbox)
		}
		if (!bF) {
			return {
				x : 0,
				y : 0,
				width : 0,
				height : 0,
				x2 : 0,
				y2 : 0
			}
		}
		bF = O(bF);
		var bB = 0, bA = 0, S = [], g = [], E;
		for (var bw = 0, bE = bF.length; bw < bE; bw++) {
			E = bF[bw];
			if (E[0] == "M") {
				bB = E[1];
				bA = E[2];
				S.push(bB);
				g.push(bA)
			} else {
				var bx = curveDim(bB, bA, E[1], E[2], E[3], E[4], E[5], E[6]);
				S = S[bn](bx.min.x, bx.max.x);
				g = g[bn](bx.min.y, bx.max.y);
				bB = E[5];
				bA = E[6]
			}
		}
		var b = a4[bq](0, S), bC = a4[bq](0, g), bv = l[bq](0, S), R = l[bq](0,
				g), d = bv - b, bD = R - bC, bz = {
			x : b,
			y : bC,
			x2 : bv,
			y2 : R,
			width : d,
			height : bD,
			cx : b + d / 2,
			cy : bC + bD / 2
		};
		by.bbox = P(bz);
		return bz
	}, aN = function(d) {
		var b = P(d);
		b.toString = aG._path2string;
		return b
	}, aq = aG._pathToRelative = function(E) {
		var bw = Q(E);
		if (bw.rel) {
			return aN(bw.rel)
		}
		if (!aG.is(E, aW) || !aG.is(E && E[0], aW)) {
			E = aG.parsePathString(E)
		}
		var bz = [], bB = 0, bA = 0, bE = 0, bD = 0, g = 0;
		if (E[0][0] == "M") {
			bB = E[0][1];
			bA = E[0][2];
			bE = bB;
			bD = bA;
			g++;
			bz.push(["M", bB, bA])
		}
		for (var bv = g, bF = E.length; bv < bF; bv++) {
			var b = bz[bv] = [], bC = E[bv];
			if (bC[0] != bu.call(bC[0])) {
				b[0] = bu.call(bC[0]);
				switch (b[0]) {
					case "a" :
						b[1] = bC[1];
						b[2] = bC[2];
						b[3] = bC[3];
						b[4] = bC[4];
						b[5] = bC[5];
						b[6] = +(bC[6] - bB).toFixed(3);
						b[7] = +(bC[7] - bA).toFixed(3);
						break;
					case "v" :
						b[1] = +(bC[1] - bA).toFixed(3);
						break;
					case "m" :
						bE = bC[1];
						bD = bC[2];
					default :
						for (var S = 1, bx = bC.length; S < bx; S++) {
							b[S] = +(bC[S] - ((S % 2) ? bB : bA)).toFixed(3)
						}
				}
			} else {
				b = bz[bv] = [];
				if (bC[0] == "m") {
					bE = bC[1] + bB;
					bD = bC[2] + bA
				}
				for (var R = 0, d = bC.length; R < d; R++) {
					bz[bv][R] = bC[R]
				}
			}
			var by = bz[bv].length;
			switch (bz[bv][0]) {
				case "z" :
					bB = bE;
					bA = bD;
					break;
				case "h" :
					bB += +bz[bv][by - 1];
					break;
				case "v" :
					bA += +bz[bv][by - 1];
					break;
				default :
					bB += +bz[bv][by - 2];
					bA += +bz[bv][by - 1]
			}
		}
		bz.toString = aG._path2string;
		bw.rel = aN(bz);
		return bz
	}, t = aG._pathToAbsolute = function(bA) {
		var g = Q(bA);
		if (g.abs) {
			return aN(g.abs)
		}
		if (!aG.is(bA, aW) || !aG.is(bA && bA[0], aW)) {
			bA = aG.parsePathString(bA)
		}
		if (!bA || !bA.length) {
			return [["M", 0, 0]]
		}
		var bG = [], bv = 0, S = 0, by = 0, bx = 0, E = 0;
		if (bA[0][0] == "M") {
			bv = +bA[0][1];
			S = +bA[0][2];
			by = bv;
			bx = S;
			E++;
			bG[0] = ["M", bv, S]
		}
		var bF = bA.length == 3 && bA[0][0] == "M"
				&& bA[1][0].toUpperCase() == "R"
				&& bA[2][0].toUpperCase() == "Z";
		for (var bz, b, bD = E, bw = bA.length; bD < bw; bD++) {
			bG.push(bz = []);
			b = bA[bD];
			if (b[0] != bb.call(b[0])) {
				bz[0] = bb.call(b[0]);
				switch (bz[0]) {
					case "A" :
						bz[1] = b[1];
						bz[2] = b[2];
						bz[3] = b[3];
						bz[4] = b[4];
						bz[5] = b[5];
						bz[6] = +(b[6] + bv);
						bz[7] = +(b[7] + S);
						break;
					case "V" :
						bz[1] = +b[1] + S;
						break;
					case "H" :
						bz[1] = +b[1] + bv;
						break;
					case "R" :
						var R = [bv, S][bn](b.slice(1));
						for (var bC = 2, bE = R.length; bC < bE; bC++) {
							R[bC] = +R[bC] + bv;
							R[++bC] = +R[bC] + S
						}
						bG.pop();
						bG = bG[bn](catmullRom2bezier(R, bF));
						break;
					case "M" :
						by = +b[1] + bv;
						bx = +b[2] + S;
					default :
						for (bC = 1, bE = b.length; bC < bE; bC++) {
							bz[bC] = +b[bC] + ((bC % 2) ? bv : S)
						}
				}
			} else {
				if (b[0] == "R") {
					R = [bv, S][bn](b.slice(1));
					bG.pop();
					bG = bG[bn](catmullRom2bezier(R, bF));
					bz = ["R"][bn](b.slice(-2))
				} else {
					for (var bB = 0, d = b.length; bB < d; bB++) {
						bz[bB] = b[bB]
					}
				}
			}
			switch (bz[0]) {
				case "Z" :
					bv = by;
					S = bx;
					break;
				case "H" :
					bv = bz[1];
					break;
				case "V" :
					S = bz[1];
					break;
				case "M" :
					by = bz[bz.length - 2];
					bx = bz[bz.length - 1];
				default :
					bv = bz[bz.length - 2];
					S = bz[bz.length - 1]
			}
		}
		bG.toString = aG._path2string;
		g.abs = aN(bG);
		return bG
	}, bs = function(d, E, b, g) {
		return [d, E, b, g, b, g]
	}, a5 = function(d, E, bv, R, b, g) {
		var S = 1 / 3, bw = 2 / 3;
		return [S * d + bw * bv, S * E + bw * R, S * b + bw * bv,
				S * g + bw * R, b, g]
	}, X = function(bC, b7, bL, bJ, bD, bx, E, bB, b6, bE) {
		var bI = aJ * 120 / 180, b = aJ / 180 * (+bD || 0), bP = [], bM, b3 = av(
				function(b9, cc, b8) {
					var cb = b9 * aj.cos(b8) - cc * aj.sin(b8), ca = b9
							* aj.sin(b8) + cc * aj.cos(b8);
					return {
						x : cb,
						y : ca
					}
				});
		if (!bE) {
			bM = b3(bC, b7, -b);
			bC = bM.x;
			b7 = bM.y;
			bM = b3(bB, b6, -b);
			bB = bM.x;
			b6 = bM.y;
			var d = aj.cos(aJ / 180 * bD), bz = aj.sin(aJ / 180 * bD), bR = (bC - bB)
					/ 2, bQ = (b7 - b6) / 2;
			var b1 = (bR * bR) / (bL * bL) + (bQ * bQ) / (bJ * bJ);
			if (b1 > 1) {
				b1 = aj.sqrt(b1);
				bL = b1 * bL;
				bJ = b1 * bJ
			}
			var g = bL * bL, bU = bJ * bJ, bW = (bx == E ? -1 : 1)
					* aj.sqrt(al((g * bU - g * bQ * bQ - bU * bR * bR)
							/ (g * bQ * bQ + bU * bR * bR))), bG = bW * bL * bQ
					/ bJ + (bC + bB) / 2, bF = bW * -bJ * bR / bL + (b7 + b6)
					/ 2, bw = aj.asin(((b7 - bF) / bJ).toFixed(9)), bv = aj
					.asin(((b6 - bF) / bJ).toFixed(9));
			bw = bC < bG ? aJ - bw : bw;
			bv = bB < bG ? aJ - bv : bv;
			bw < 0 && (bw = aJ * 2 + bw);
			bv < 0 && (bv = aJ * 2 + bv);
			if (E && bw > bv) {
				bw = bw - aJ * 2
			}
			if (!E && bv > bw) {
				bv = bv - aJ * 2
			}
		} else {
			bw = bE[0];
			bv = bE[1];
			bG = bE[2];
			bF = bE[3]
		}
		var bA = bv - bw;
		if (al(bA) > bI) {
			var bH = bv, bK = bB, by = b6;
			bv = bw + bI * (E && bv > bw ? 1 : -1);
			bB = bG + bL * aj.cos(bv);
			b6 = bF + bJ * aj.sin(bv);
			bP = X(bB, b6, bL, bJ, bD, 0, E, bK, by, [bv, bH, bG, bF])
		}
		bA = bv - bw;
		var S = aj.cos(bw), b5 = aj.sin(bw), R = aj.cos(bv), b4 = aj.sin(bv), bS = aj
				.tan(bA / 4), bV = 4 / 3 * bL * bS, bT = 4 / 3 * bJ * bS, b2 = [
				bC, b7], b0 = [bC + bV * b5, b7 - bT * S], bZ = [bB + bV * b4,
				b6 - bT * R], bX = [bB, b6];
		b0[0] = 2 * b2[0] - b0[0];
		b0[1] = 2 * b2[1] - b0[1];
		if (bE) {
			return [b0, bZ, bX][bn](bP)
		} else {
			bP = [b0, bZ, bX][bn](bP).join()[z](",");
			var bN = [];
			for (var bY = 0, bO = bP.length; bY < bO; bY++) {
				bN[bY] = bY % 2 ? b3(bP[bY - 1], bP[bY], b).y : b3(bP[bY],
						bP[bY + 1], b).x
			}
			return bN
		}
	}, O = aG._path2curve = av(function(bE, bz) {
		var bx = !bz && Q(bE);
		if (!bz && bx.curve) {
			return aN(bx.curve)
		}
		var E = t(bE), bA = bz && t(bz), bB = {
			x : 0,
			y : 0,
			bx : 0,
			by : 0,
			X : 0,
			Y : 0,
			qx : null,
			qy : null
		}, d = {
			x : 0,
			y : 0,
			bx : 0,
			by : 0,
			X : 0,
			Y : 0,
			qx : null,
			qy : null
		}, S = function(bI, bJ, bG) {
			var bF, bK, bH = {
				T : 1,
				Q : 1
			};
			if (!bI) {
				return ["C", bJ.x, bJ.y, bJ.x, bJ.y, bJ.x, bJ.y]
			}
			!(bI[0] in bH) && (bJ.qx = bJ.qy = null);
			switch (bI[0]) {
				case "M" :
					bJ.X = bI[1];
					bJ.Y = bI[2];
					break;
				case "A" :
					bI = ["C"][bn](X[bq](0, [bJ.x, bJ.y][bn](bI.slice(1))));
					break;
				case "S" :
					if (bG == "C" || bG == "S") {
						bF = bJ.x * 2 - bJ.bx;
						bK = bJ.y * 2 - bJ.by
					} else {
						bF = bJ.x;
						bK = bJ.y
					}
					bI = ["C", bF, bK][bn](bI.slice(1));
					break;
				case "T" :
					if (bG == "Q" || bG == "T") {
						bJ.qx = bJ.x * 2 - bJ.qx;
						bJ.qy = bJ.y * 2 - bJ.qy
					} else {
						bJ.qx = bJ.x;
						bJ.qy = bJ.y
					}
					bI = ["C"][bn](a5(bJ.x, bJ.y, bJ.qx, bJ.qy, bI[1], bI[2]));
					break;
				case "Q" :
					bJ.qx = bI[1];
					bJ.qy = bI[2];
					bI = ["C"][bn](a5(bJ.x, bJ.y, bI[1], bI[2], bI[3], bI[4]));
					break;
				case "L" :
					bI = ["C"][bn](bs(bJ.x, bJ.y, bI[1], bI[2]));
					break;
				case "H" :
					bI = ["C"][bn](bs(bJ.x, bJ.y, bI[1], bJ.y));
					break;
				case "V" :
					bI = ["C"][bn](bs(bJ.x, bJ.y, bJ.x, bI[1]));
					break;
				case "Z" :
					bI = ["C"][bn](bs(bJ.x, bJ.y, bJ.X, bJ.Y));
					break
			}
			return bI
		}, b = function(bF, bG) {
			if (bF[bG].length > 7) {
				bF[bG].shift();
				var bH = bF[bG];
				while (bH.length) {
					bF.splice(bG++, 0, ["C"][bn](bH.splice(0, 6)))
				}
				bF.splice(bG, 1);
				bC = l(E.length, bA && bA.length || 0)
			}
		}, g = function(bJ, bI, bG, bF, bH) {
			if (bJ && bI && bJ[bH][0] == "M" && bI[bH][0] != "M") {
				bI.splice(bH, 0, ["M", bF.x, bF.y]);
				bG.bx = 0;
				bG.by = 0;
				bG.x = bJ[bH][1];
				bG.y = bJ[bH][2];
				bC = l(E.length, bA && bA.length || 0)
			}
		};
		for (var bw = 0, bC = l(E.length, bA && bA.length || 0); bw < bC; bw++) {
			E[bw] = S(E[bw], bB);
			b(E, bw);
			bA && (bA[bw] = S(bA[bw], d));
			bA && b(bA, bw);
			g(E, bA, bB, d, bw);
			g(bA, E, d, bB, bw);
			var bv = E[bw], bD = bA && bA[bw], R = bv.length, by = bA
					&& bD.length;
			bB.x = bv[R - 2];
			bB.y = bv[R - 1];
			bB.bx = ae(bv[R - 4]) || bB.x;
			bB.by = ae(bv[R - 3]) || bB.y;
			d.bx = bA && (ae(bD[by - 4]) || d.x);
			d.by = bA && (ae(bD[by - 3]) || d.y);
			d.x = bA && bD[by - 2];
			d.y = bA && bD[by - 1]
		}
		if (!bA) {
			bx.curve = aN(E)
		}
		return bA ? [E, bA] : E
	}, null, aN), s = aG._parseDots = av(function(by) {
				var bx = [];
				for (var S = 0, bz = by.length; S < bz; S++) {
					var b = {}, bw = by[S].match(/^([^:]*):?([\d\.]*)/);
					b.color = aG.getRGB(bw[1]);
					if (b.color.error) {
						return null
					}
					b.color = b.color.hex;
					bw[2] && (b.offset = bw[2] + "%");
					bx.push(b)
				}
				for (S = 1, bz = bx.length - 1; S < bz; S++) {
					if (!bx[S].offset) {
						var g = ae(bx[S - 1].offset || 0), E = 0;
						for (var R = S + 1; R < bz; R++) {
							if (bx[R].offset) {
								E = bx[R].offset;
								break
							}
						}
						if (!E) {
							E = 100;
							R = bz
						}
						E = ae(E);
						var bv = (E - g) / (R - S + 1);
						for (; S < R; S++) {
							g += bv;
							bx[S].offset = g + "%"
						}
					}
				}
				return bx
			}), az = aG._tear = function(b, d) {
		b == d.top && (d.top = b.prev);
		b == d.bottom && (d.bottom = b.next);
		b.next && (b.next.prev = b.prev);
		b.prev && (b.prev.next = b.next)
	}, ag = aG._tofront = function(b, d) {
		if (d.top === b) {
			return
		}
		az(b, d);
		b.next = null;
		b.prev = d.top;
		d.top.next = b;
		d.top = b
	}, o = aG._toback = function(b, d) {
		if (d.bottom === b) {
			return
		}
		az(b, d);
		b.next = d.bottom;
		b.prev = null;
		d.bottom.prev = b;
		d.bottom = b
	}, A = aG._insertafter = function(d, b, g) {
		az(d, g);
		b == g.top && (g.top = d);
		b.next && (b.next.prev = d);
		d.next = b.next;
		d.prev = b;
		b.next = d
	}, aH = aG._insertbefore = function(d, b, g) {
		az(d, g);
		b == g.bottom && (g.bottom = d);
		b.prev && (b.prev.next = d);
		d.prev = b.prev;
		b.prev = d;
		d.next = b
	}, a3 = aG.toMatrix = function(g, b) {
		var E = ad(g), d = {
			_ : {
				transform : aL
			},
			getBBox : function() {
				return E
			}
		};
		aD(d, b);
		return d.matrix
	}, L = aG.transformPath = function(d, b) {
		return F(d, a3(d, b))
	}, aD = aG._extractTransform = function(d, bJ) {
		if (bJ == null) {
			return d._.transform
		}
		bJ = br(bJ).replace(/\.{3}|\u2026/g, d._.transform || aL);
		var bB = aG.parseTransformString(bJ), bz = 0, bx = 0, bw = 0, bD = 1, bC = 1, bK = d._, bE = new au;
		bK.transform = bB || [];
		if (bB) {
			for (var bF = 0, by = bB.length; bF < by; bF++) {
				var bA = bB[bF], b = bA.length, R = br(bA[0]).toLowerCase(), bI = bA[0] != R, bv = bI
						? bE.invert()
						: 0, bH, E, bG, g, S;
				if (R == "t" && b == 3) {
					if (bI) {
						bH = bv.x(0, 0);
						E = bv.y(0, 0);
						bG = bv.x(bA[1], bA[2]);
						g = bv.y(bA[1], bA[2]);
						bE.translate(bG - bH, g - E)
					} else {
						bE.translate(bA[1], bA[2])
					}
				} else {
					if (R == "r") {
						if (b == 2) {
							S = S || d.getBBox(1);
							bE.rotate(bA[1], S.x + S.width / 2, S.y + S.height
											/ 2);
							bz += bA[1]
						} else {
							if (b == 4) {
								if (bI) {
									bG = bv.x(bA[2], bA[3]);
									g = bv.y(bA[2], bA[3]);
									bE.rotate(bA[1], bG, g)
								} else {
									bE.rotate(bA[1], bA[2], bA[3])
								}
								bz += bA[1]
							}
						}
					} else {
						if (R == "s") {
							if (b == 2 || b == 3) {
								S = S || d.getBBox(1);
								bE.scale(bA[1], bA[b - 1], S.x + S.width / 2,
										S.y + S.height / 2);
								bD *= bA[1];
								bC *= bA[b - 1]
							} else {
								if (b == 5) {
									if (bI) {
										bG = bv.x(bA[3], bA[4]);
										g = bv.y(bA[3], bA[4]);
										bE.scale(bA[1], bA[2], bG, g)
									} else {
										bE.scale(bA[1], bA[2], bA[3], bA[4])
									}
									bD *= bA[1];
									bC *= bA[2]
								}
							}
						} else {
							if (R == "m" && b == 7) {
								bE
										.add(bA[1], bA[2], bA[3], bA[4], bA[5],
												bA[6])
							}
						}
					}
				}
				bK.dirtyT = 1;
				d.matrix = bE
			}
		}
		d.matrix = bE;
		bK.sx = bD;
		bK.sy = bC;
		bK.deg = bz;
		bK.dx = bx = bE.e;
		bK.dy = bw = bE.f;
		if (bD == 1 && bC == 1 && !bz && bK.bbox) {
			bK.bbox.x += +bx;
			bK.bbox.y += +bw
		} else {
			bK.dirtyT = 1
		}
	}, k = function(d) {
		var b = d[0];
		switch (b.toLowerCase()) {
			case "t" :
				return [b, 0, 0];
			case "m" :
				return [b, 1, 0, 0, 1, 0, 0];
			case "r" :
				if (d.length == 4) {
					return [b, 0, d[2], d[3]]
				} else {
					return [b, 0]
				}
			case "s" :
				if (d.length == 5) {
					return [b, 1, 1, d[3], d[4]]
				} else {
					if (d.length == 3) {
						return [b, 1, 1]
					} else {
						return [b, 1]
					}
				}
		}
	}, ap = aG._equaliseTransform = function(R, E) {
		E = br(E).replace(/\.{3}|\u2026/g, R);
		R = aG.parseTransformString(R) || [];
		E = aG.parseTransformString(E) || [];
		var b = l(R.length, E.length), bx = [], by = [], g = 0, d, S, bw, bv;
		for (; g < b; g++) {
			bw = R[g] || k(E[g]);
			bv = E[g] || k(bw);
			if ((bw[0] != bv[0])
					|| (bw[0].toLowerCase() == "r" && (bw[2] != bv[2] || bw[3] != bv[3]))
					|| (bw[0].toLowerCase() == "s" && (bw[3] != bv[3] || bw[4] != bv[4]))) {
				return
			}
			bx[g] = [];
			by[g] = [];
			for (d = 0, S = l(bw.length, bv.length); d < S; d++) {
				d in bw && (bx[g][d] = bw[d]);
				d in bv && (by[g][d] = bv[d])
			}
		}
		return {
			from : bx,
			to : by
		}
	};
	aG._getContainer = function(b, R, g, E) {
		var d;
		d = E == null && !aG.is(b, "object") ? ao.doc.getElementById(b) : b;
		if (d == null) {
			return
		}
		if (d.tagName) {
			if (R == null) {
				return {
					container : d,
					width : d.style.pixelWidth || d.offsetWidth,
					height : d.style.pixelHeight || d.offsetHeight
				}
			} else {
				return {
					container : d,
					width : R,
					height : g
				}
			}
		}
		return {
			container : 1,
			x : b,
			y : R,
			width : g,
			height : E
		}
	};
	aG.pathToRelative = aq;
	aG._engine = {};
	aG.path2curve = O;
	aG.matrix = function(E, g, bw, bv, S, R) {
		return new au(E, g, bw, bv, S, R)
	};
	function au(E, g, bw, bv, S, R) {
		if (E != null) {
			this.a = +E;
			this.b = +g;
			this.c = +bw;
			this.d = +bv;
			this.e = +S;
			this.f = +R
		} else {
			this.a = 1;
			this.b = 0;
			this.c = 0;
			this.d = 1;
			this.e = 0;
			this.f = 0
		}
	}
(function(g) {
		g.add = function(bE, bB, bz, bx, bv, S) {
			var R = [[], [], []], E = [[this.a, this.c, this.e],
					[this.b, this.d, this.f], [0, 0, 1]], bD = [[bE, bz, bv],
					[bB, bx, S], [0, 0, 1]], bC, bA, by, bw;
			if (bE && bE instanceof au) {
				bD = [[bE.a, bE.c, bE.e], [bE.b, bE.d, bE.f], [0, 0, 1]]
			}
			for (bC = 0; bC < 3; bC++) {
				for (bA = 0; bA < 3; bA++) {
					bw = 0;
					for (by = 0; by < 3; by++) {
						bw += E[bC][by] * bD[by][bA]
					}
					R[bC][bA] = bw
				}
			}
			this.a = R[0][0];
			this.b = R[1][0];
			this.c = R[0][1];
			this.d = R[1][1];
			this.e = R[0][2];
			this.f = R[1][2]
		};
		g.invert = function() {
			var R = this, E = R.a * R.d - R.b * R.c;
			return new au(R.d / E, -R.b / E, -R.c / E, R.a / E,
					(R.c * R.f - R.d * R.e) / E, (R.b * R.e - R.a * R.f) / E)
		};
		g.clone = function() {
			return new au(this.a, this.b, this.c, this.d, this.e, this.f)
		};
		g.translate = function(E, R) {
			this.add(1, 0, 0, 1, E, R)
		};
		g.scale = function(R, bv, E, S) {
			bv == null && (bv = R);
			(E || S) && this.add(1, 0, 0, 1, E, S);
			this.add(R, 0, 0, bv, 0, 0);
			(E || S) && this.add(1, 0, 0, 1, -E, -S)
		};
		g.rotate = function(R, E, bw) {
			R = aG.rad(R);
			E = E || 0;
			bw = bw || 0;
			var bv = +aj.cos(R).toFixed(9), S = +aj.sin(R).toFixed(9);
			this.add(bv, S, -S, bv, E, bw);
			this.add(1, 0, 0, 1, -E, -bw)
		};
		g.x = function(E, R) {
			return E * this.a + R * this.c + this.e
		};
		g.y = function(E, R) {
			return E * this.b + R * this.d + this.f
		};
		g.get = function(E) {
			return +this[br.fromCharCode(97 + E)].toFixed(4)
		};
		g.toString = function() {
			return aG.svg ? "matrix("
					+ [this.get(0), this.get(1), this.get(2), this.get(3),
							this.get(4), this.get(5)].join() + ")" : [
					this.get(0), this.get(2), this.get(1), this.get(3), 0, 0]
					.join()
		};
		g.toFilter = function() {
			return "progid:DXImageTransform.Microsoft.Matrix(M11="
					+ this.get(0) + ", M12=" + this.get(2) + ", M21="
					+ this.get(1) + ", M22=" + this.get(3) + ", Dx="
					+ this.get(4) + ", Dy=" + this.get(5)
					+ ", sizingmethod='auto expand')"
		};
		g.offset = function() {
			return [this.e.toFixed(4), this.f.toFixed(4)]
		};
		function d(E) {
			return E[0] * E[0] + E[1] * E[1]
		}
		function b(E) {
			var R = aj.sqrt(d(E));
			E[0] && (E[0] /= R);
			E[1] && (E[1] /= R)
		}
		g.split = function() {
			var R = {};
			R.dx = this.e;
			R.dy = this.f;
			var bv = [[this.a, this.c], [this.b, this.d]];
			R.scalex = aj.sqrt(d(bv[0]));
			b(bv[0]);
			R.shear = bv[0][0] * bv[1][0] + bv[0][1] * bv[1][1];
			bv[1] = [bv[1][0] - bv[0][0] * R.shear,
					bv[1][1] - bv[0][1] * R.shear];
			R.scaley = aj.sqrt(d(bv[1]));
			b(bv[1]);
			R.shear /= R.scaley;
			var E = -bv[0][1], S = bv[1][1];
			if (S < 0) {
				R.rotate = aG.deg(aj.acos(S));
				if (E < 0) {
					R.rotate = 360 - R.rotate
				}
			} else {
				R.rotate = aG.deg(aj.asin(E))
			}
			R.isSimple = !+R.shear.toFixed(9)
					&& (R.scalex.toFixed(9) == R.scaley.toFixed(9) || !R.rotate);
			R.isSuperSimple = !+R.shear.toFixed(9)
					&& R.scalex.toFixed(9) == R.scaley.toFixed(9) && !R.rotate;
			R.noRotation = !+R.shear.toFixed(9) && !R.rotate;
			return R
		};
		g.toTransformString = function(E) {
			var R = E || this[z]();
			if (R.isSimple) {
				R.scalex = +R.scalex.toFixed(4);
				R.scaley = +R.scaley.toFixed(4);
				R.rotate = +R.rotate.toFixed(4);
				return (R.dx || R.dy ? "t" + [R.dx, R.dy] : aL)
						+ (R.scalex != 1 || R.scaley != 1 ? "s"
								+ [R.scalex, R.scaley, 0, 0] : aL)
						+ (R.rotate ? "r" + [R.rotate, 0, 0] : aL)
			} else {
				return "m"
						+ [this.get(0), this.get(1), this.get(2), this.get(3),
								this.get(4), this.get(5)]
			}
		}
	})(au.prototype);
	var N = navigator.userAgent.match(/Version\/(.*?)\s/)
			|| navigator.userAgent.match(/Chrome\/(\d+)/);
	if ((navigator.vendor == "Apple Computer, Inc.")
			&& (N && N[1] < 4 || navigator.platform.slice(0, 2) == "iP")
			|| (navigator.vendor == "Google Inc." && N && N[1] < 8)) {
		aR.safari = function() {
			var b = this.rect(-99, -99, this.width + 99, this.height + 99)
					.attr({
								stroke : "none"
							});
			setTimeout(function() {
						b.remove()
					})
		}
	} else {
		aR.safari = C
	}
	var I = function() {
		this.returnValue = false
	}, bm = function() {
		return this.originalEvent.preventDefault()
	}, aT = function() {
		this.cancelBubble = true
	}, ay = function() {
		return this.originalEvent.stopPropagation()
	}, bp = function(d) {
		var b = ao.doc.documentElement.scrollTop || ao.doc.body.scrollTop, g = ao.doc.documentElement.scrollLeft
				|| ao.doc.body.scrollLeft;
		return {
			x : d.clientX + g,
			y : d.clientY + b
		}
	}, ar = (function() {
		if (ao.doc.addEventListener) {
			return function(R, g, d, b) {
				var E = function(bv) {
					var bw = bp(bv);
					return d.call(b, bv, bw.x, bw.y)
				};
				R.addEventListener(g, E, false);
				if (T && bg[g]) {
					var S = function(by) {
						var bz = bp(by), bw = by;
						for (var bv = 0, bx = by.targetTouches
								&& by.targetTouches.length; bv < bx; bv++) {
							if (by.targetTouches[bv].target == R) {
								by = by.targetTouches[bv];
								by.originalEvent = bw;
								by.preventDefault = bm;
								by.stopPropagation = ay;
								break
							}
						}
						return d.call(b, by, bz.x, bz.y)
					};
					R.addEventListener(bg[g], S, false)
				}
				return function() {
					R.removeEventListener(g, E, false);
					if (T && bg[g]) {
						R.removeEventListener(bg[g], E, false)
					}
					return true
				}
			}
		} else {
			if (ao.doc.attachEvent) {
				return function(S, E, g, d) {
					var R = function(bx) {
						bx = bx || ao.win.event;
						var bw = ao.doc.documentElement.scrollTop
								|| ao.doc.body.scrollTop, by = ao.doc.documentElement.scrollLeft
								|| ao.doc.body.scrollLeft, bv = bx.clientX + by, bz = bx.clientY
								+ bw;
						bx.preventDefault = bx.preventDefault || I;
						bx.stopPropagation = bx.stopPropagation || aT;
						return g.call(d, bx, bv, bz)
					};
					S.attachEvent("on" + E, R);
					var b = function() {
						S.detachEvent("on" + E, R);
						return true
					};
					return b
				}
			}
		}
	})(), aX = [], bh = function(bw) {
		var bz = bw.clientX, by = bw.clientY, bB = ao.doc.documentElement.scrollTop
				|| ao.doc.body.scrollTop, bC = ao.doc.documentElement.scrollLeft
				|| ao.doc.body.scrollLeft, g, E = aX.length;
		while (E--) {
			g = aX[E];
			if (T && bw.touches) {
				var S = bw.touches.length, R;
				while (S--) {
					R = bw.touches[S];
					if (R.identifier == g.el._drag.id) {
						bz = R.clientX;
						by = R.clientY;
						(bw.originalEvent ? bw.originalEvent : bw)
								.preventDefault();
						break
					}
				}
			} else {
				bw.preventDefault()
			}
			var d = g.el.node, b, bv = d.nextSibling, bA = d.parentNode, bx = d.style.display;
			ao.win.opera && bA.removeChild(d);
			d.style.display = "none";
			b = g.el.paper.getElementByPoint(bz, by);
			d.style.display = bx;
			ao.win.opera && (bv ? bA.insertBefore(d, bv) : bA.appendChild(d));
			b && u("drag.drag.over." + g.el.id, g.el, b);
			bz += bC;
			by += bB;
			u("drag.drag.move." + g.el.id, g.move_scope || g.el, bz
							- g.el._drag.x, by - g.el._drag.y, bz, by, bw)
		}
	}, e = function(g) {
		aG.unmousemove(bh).unmouseup(e);
		var d = aX.length, b;
		while (d--) {
			b = aX[d];
			b.el._drag = {};
			u("drag.drag.end." + b.el.id, b.end_scope || b.start_scope
							|| b.move_scope || b.el, g)
		}
		aX = []
	}, a0 = aG.el = {};
	for (var am = K.length; am--;) {
(function(b) {
			aG[b] = a0[b] = function(g, d) {
				if (aG.is(g, "function")) {
					this.events = this.events || [];
					this.events.push({
								name : b,
								f : g,
								unbind : ar(this.shape || this.node || ao.doc,
										b, g, d || this)
							})
				}
				return this
			};
			aG["un" + b] = a0["un" + b] = function(E) {
				var g = this.events || [], d = g.length;
				while (d--) {
					if (g[d].name == b
							&& (aG.is(E, "undefined") || g[d].f == E)) {
						g[d].unbind();
						g.splice(d, 1);
						!g.length && delete this.events
					}
				}
				return this
			}
		})(K[am])
	}
	a0.data = function(d, E) {
		var g = aI[this.id] = aI[this.id] || {};
		if (arguments.length == 0) {
			return g
		}
		if (arguments.length == 1) {
			if (aG.is(d, "object")) {
				for (var b in d) {
					if (d[ab](b)) {
						this.data(b, d[b])
					}
				}
				return this
			}
			u("drag.data.get." + this.id, this, g[d], d);
			return g[d]
		}
		g[d] = E;
		u("drag.data.set." + this.id, this, E, d);
		return this
	};
	a0.removeData = function(b) {
		if (b == null) {
			aI[this.id] = {}
		} else {
			aI[this.id] && delete aI[this.id][b]
		}
		return this
	};
	var bc = [];
	aR.circle = function(b, E, g) {
		var d = aG._engine.circle(this, b || 0, E || 0, g || 0);
		this.__set__ && this.__set__.push(d);
		return d
	};
	aR.rect = function(b, S, d, E, R) {
		var g = aG._engine.rect(this, b || 0, S || 0, d || 0, E || 0, R || 0);
		this.__set__ && this.__set__.push(g);
		return g
	};
	aR.ellipse = function(b, R, E, g) {
		var d = aG._engine.ellipse(this, b || 0, R || 0, E || 0, g || 0);
		this.__set__ && this.__set__.push(d);
		return d
	};
	aR.path = function(b) {
		b && !aG.is(b, aa) && !aG.is(b[0], aW) && (b += aL);
		var d = aG._engine.path(aG.format[bq](aG, arguments), this);
		this.__set__ && this.__set__.push(d);
		return d
	};
	aR.image = function(R, b, S, d, E) {
		var g = aG._engine.image(this, R || "about:blank", b || 0, S || 0, d
						|| 0, E || 0);
		this.__set__ && this.__set__.push(g);
		return g
	};
	aR.text = function(b, E, g) {
		var d = aG._engine.text(this, b || 0, E || 0, br(g));
		this.__set__ && this.__set__.push(d);
		return d
	};
	aR.set = function(d) {
		!aG.is(d, "array")
				&& (d = Array.prototype.splice.call(arguments, 0,
						arguments.length));
		var b = new ac(d);
		this.__set__ && this.__set__.push(b);
		b.paper = this;
		b.type = "set";
		return b
	};
	aR.setStart = function(b) {
		this.__set__ = b || this.set()
	};
	aR.setFinish = function(d) {
		var b = this.__set__;
		delete this.__set__;
		return b
	};
	aR.setSize = function(d, b) {
		return aG._engine.setSize.call(this, d, b)
	};
	aR.setViewBox = function(b, R, d, E, g) {
		return aG._engine.setViewBox.call(this, b, R, d, E, g)
	};
	aR.top = aR.bottom = null;
	aR.drag = aG;
	var ba = function(g) {
		var R = g.getBoundingClientRect(), bx = g.ownerDocument, S = bx.body, b = bx.documentElement, E = b.clientTop
				|| S.clientTop || 0, bv = b.clientLeft || S.clientLeft || 0, bw = R.top
				+ (ao.win.pageYOffset || b.scrollTop || S.scrollTop) - E, d = R.left
				+ (ao.win.pageXOffset || b.scrollLeft || S.scrollLeft) - bv;
		return {
			y : bw,
			x : d
		}
	};
	var n = aG.easing_formulas = {
		linear : function(b) {
			return b
		},
		"<" : function(b) {
			return a7(b, 1.7)
		},
		">" : function(b) {
			return a7(b, 0.48)
		},
		"<>" : function(bw) {
			var E = 0.48 - bw / 1.04, g = aj.sqrt(0.1734 + E * E), b = g - E, bv = a7(
					al(b), 1 / 3)
					* (b < 0 ? -1 : 1), S = -g - E, R = a7(al(S), 1 / 3)
					* (S < 0 ? -1 : 1), d = bv + R + 0.5;
			return (1 - d) * 3 * d * d + d * d * d
		},
		backIn : function(d) {
			var b = 1.70158;
			return d * d * ((b + 1) * d - b)
		},
		backOut : function(d) {
			d = d - 1;
			var b = 1.70158;
			return d * d * ((b + 1) * d + b) + 1
		},
		elastic : function(b) {
			if (b == !!b) {
				return b
			}
			return a7(2, -10 * b) * aj.sin((b - 0.075) * (2 * aJ) / 0.3) + 1
		},
		bounce : function(E) {
			var d = 7.5625, g = 2.75, b;
			if (E < (1 / g)) {
				b = d * E * E
			} else {
				if (E < (2 / g)) {
					E -= (1.5 / g);
					b = d * E * E + 0.75
				} else {
					if (E < (2.5 / g)) {
						E -= (2.25 / g);
						b = d * E * E + 0.9375
					} else {
						E -= (2.625 / g);
						b = d * E * E + 0.984375
					}
				}
			}
			return b
		}
	};
	n.easeIn = n["ease-in"] = n["<"];
	n.easeOut = n["ease-out"] = n[">"];
	n.easeInOut = n["ease-in-out"] = n["<>"];
	n["back-in"] = n.backIn;
	n["back-out"] = n.backOut;
	var V = [], aC = be.requestAnimationFrame || be.webkitRequestAnimationFrame
			|| be.mozRequestAnimationFrame || be.oRequestAnimationFrame
			|| be.msRequestAnimationFrame || function(b) {
				setTimeout(b, 16)
			}, bl = function() {
		var bv = +new Date, bD = 0;
		for (; bD < V.length; bD++) {
			var bJ = V[bD];
			if (bJ.el.removed || bJ.paused) {
				continue
			}
			var E = bv - bJ.start, bB = bJ.ms, bA = bJ.easing, bE = bJ.from, by = bJ.diff, d = bJ.to, bx = bJ.t, S = bJ.el, bz = {}, b, bH = {}, bL;
			if (bJ.initstatus) {
				E = (bJ.initstatus * bJ.anim.top - bJ.prev)
						/ (bJ.percent - bJ.prev) * bB;
				bJ.status = bJ.initstatus;
				delete bJ.initstatus;
				bJ.stop && V.splice(bD--, 1)
			} else {
				bJ.status = (bJ.prev + (bJ.percent - bJ.prev) * (E / bB))
						/ bJ.anim.top
			}
			if (E < 0) {
				continue
			}
			if (E < bB) {
				var g = bA(E / bB);
				for (var bC in bE) {
					if (bE[ab](bC)) {
						switch (ai[bC]) {
							case aA :
								b = +bE[bC] + g * bB * by[bC];
								break;
							case "colour" :
								b = "rgb("
										+ [
												B(Y(bE[bC].r + g * bB
														* by[bC].r)),
												B(Y(bE[bC].g + g * bB
														* by[bC].g)),
												B(Y(bE[bC].b + g * bB
														* by[bC].b))].join(",")
										+ ")";
								break;
							case "path" :
								b = [];
								for (var bG = 0, bw = bE[bC].length; bG < bw; bG++) {
									b[bG] = [bE[bC][bG][0]];
									for (var bF = 1, bI = bE[bC][bG].length; bF < bI; bF++) {
										b[bG][bF] = +bE[bC][bG][bF] + g * bB
												* by[bC][bG][bF]
									}
									b[bG] = b[bG].join(aF)
								}
								b = b.join(aF);
								break;
							case "transform" :
								if (by[bC].real) {
									b = [];
									for (bG = 0, bw = bE[bC].length; bG < bw; bG++) {
										b[bG] = [bE[bC][bG][0]];
										for (bF = 1, bI = bE[bC][bG].length; bF < bI; bF++) {
											b[bG][bF] = bE[bC][bG][bF] + g * bB
													* by[bC][bG][bF]
										}
									}
								} else {
									var bK = function(bM) {
										return +bE[bC][bM] + g * bB
												* by[bC][bM]
									};
									b = [["m", bK(0), bK(1), bK(2), bK(3),
											bK(4), bK(5)]]
								}
								break;
							case "csv" :
								if (bC == "clip-rect") {
									b = [];
									bG = 4;
									while (bG--) {
										b[bG] = +bE[bC][bG] + g * bB
												* by[bC][bG]
									}
								}
								break;
							default :
								var R = [][bn](bE[bC]);
								b = [];
								bG = S.paper.customAttributes[bC].length;
								while (bG--) {
									b[bG] = +R[bG] + g * bB * by[bC][bG]
								}
								break
						}
						bz[bC] = b
					}
				}
				S.attr(bz);
				(function(bO, bM, bN) {
					setTimeout(function() {
								u("drag.anim.frame." + bO, bM, bN)
							})
				})(S.id, S, bJ.anim)
			} else {
				(function(bO, bN, bM) {
					setTimeout(function() {
								u("drag.anim.frame." + bN.id, bN, bM);
								u("drag.anim.finish." + bN.id, bN, bM);
								aG.is(bO, "function") && bO.call(bN)
							})
				})(bJ.callback, S, bJ.anim);
				S.attr(d);
				V.splice(bD--, 1);
				if (bJ.repeat > 1 && !bJ.next) {
					for (bL in d) {
						if (d[ab](bL)) {
							bH[bL] = bJ.totalOrigin[bL]
						}
					}
					bJ.el.attr(bH);
					aB(bJ.anim, bJ.el, bJ.anim.percents[0], null,
							bJ.totalOrigin, bJ.repeat - 1)
				}
				if (bJ.next && !bJ.stop) {
					aB(bJ.anim, bJ.el, bJ.next, null, bJ.totalOrigin, bJ.repeat)
				}
			}
		}
		aG.svg && S && S.paper && S.paper.safari();
		V.length && aC(bl)
	}, B = function(b) {
		return b > 255 ? 255 : b < 0 ? 0 : b
	};
	a0.onAnimation = function(b) {
		b ? u.on("drag.anim.frame." + this.id, b) : u.unbind("drag.anim.frame."
				+ this.id);
		return this
	};
	function bj(R, g) {
		var d = [], E = {};
		this.ms = g;
		this.times = 1;
		if (R) {
			for (var b in R) {
				if (R[ab](b)) {
					E[ae(b)] = R[b];
					d.push(ae(b))
				}
			}
			d.sort(r)
		}
		this.anim = E;
		this.top = d[d.length - 1];
		this.percents = d
	}
	bj.prototype.delay = function(d) {
		var b = new bj(this.anim, this.ms);
		b.times = this.times;
		b.del = +d || 0;
		return b
	};
	bj.prototype.repeat = function(d) {
		var b = new bj(this.anim, this.ms);
		b.del = this.del;
		b.times = aj.floor(l(d, 0)) || 1;
		return b
	};
	function aB(bN, g, b, bL, bv, bz) {
		b = ae(b);
		var bU, S, by, bV = [], bF, bE, R, bH = bN.ms, bM = {}, E = {}, bB = {};
		if (bL) {
			for (bQ = 0, bA = V.length; bQ < bA; bQ++) {
				var bS = V[bQ];
				if (bS.el.id == g.id && bS.anim == bN) {
					if (bS.percent != b) {
						V.splice(bQ, 1);
						by = 1
					} else {
						S = bS
					}
					g.attr(bS.totalOrigin);
					break
				}
			}
		} else {
			bL = +E
		}
		for (var bQ = 0, bA = bN.percents.length; bQ < bA; bQ++) {
			if (bN.percents[bQ] == b || bN.percents[bQ] > bL * bN.top) {
				b = bN.percents[bQ];
				bE = bN.percents[bQ - 1] || 0;
				bH = bH / bN.top * (b - bE);
				bF = bN.percents[bQ + 1];
				bU = bN.anim[b];
				break
			} else {
				if (bL) {
					g.attr(bN.anim[bN.percents[bQ]])
				}
			}
		}
		if (!bU) {
			return
		}
		if (!S) {
			for (var bJ in bU) {
				if (bU[ab](bJ)) {
					if (ai[ab](bJ) || g.paper.customAttributes[ab](bJ)) {
						bM[bJ] = g.attr(bJ);
						(bM[bJ] == null) && (bM[bJ] = p[bJ]);
						E[bJ] = bU[bJ];
						switch (ai[bJ]) {
							case aA :
								bB[bJ] = (E[bJ] - bM[bJ]) / bH;
								break;
							case "colour" :
								bM[bJ] = aG.getRGB(bM[bJ]);
								var bK = aG.getRGB(E[bJ]);
								bB[bJ] = {
									r : (bK.r - bM[bJ].r) / bH,
									g : (bK.g - bM[bJ].g) / bH,
									b : (bK.b - bM[bJ].b) / bH
								};
								break;
							case "path" :
								var bw = O(bM[bJ], E[bJ]), bD = bw[1];
								bM[bJ] = bw[0];
								bB[bJ] = [];
								for (bQ = 0, bA = bM[bJ].length; bQ < bA; bQ++) {
									bB[bJ][bQ] = [0];
									for (var bP = 1, bR = bM[bJ][bQ].length; bP < bR; bP++) {
										bB[bJ][bQ][bP] = (bD[bQ][bP] - bM[bJ][bQ][bP])
												/ bH
									}
								}
								break;
							case "transform" :
								var bX = g._, bW = ap(bX[bJ], E[bJ]);
								if (bW) {
									bM[bJ] = bW.from;
									E[bJ] = bW.to;
									bB[bJ] = [];
									bB[bJ].real = true;
									for (bQ = 0, bA = bM[bJ].length; bQ < bA; bQ++) {
										bB[bJ][bQ] = [bM[bJ][bQ][0]];
										for (bP = 1, bR = bM[bJ][bQ].length; bP < bR; bP++) {
											bB[bJ][bQ][bP] = (E[bJ][bQ][bP] - bM[bJ][bQ][bP])
													/ bH
										}
									}
								} else {
									var bI = (g.matrix || new au), bT = {
										_ : {
											transform : bX.transform
										},
										getBBox : function() {
											return g.getBBox(1)
										}
									};
									bM[bJ] = [bI.a, bI.b, bI.c, bI.d, bI.e,
											bI.f];
									aD(bT, E[bJ]);
									E[bJ] = bT._.transform;
									bB[bJ] = [(bT.matrix.a - bI.a) / bH,
											(bT.matrix.b - bI.b) / bH,
											(bT.matrix.c - bI.c) / bH,
											(bT.matrix.d - bI.d) / bH,
											(bT.matrix.e - bI.e) / bH,
											(bT.matrix.f - bI.f) / bH]
								}
								break;
							case "csv" :
								var d = br(bU[bJ])[z](a), bx = br(bM[bJ])[z](a);
								if (bJ == "clip-rect") {
									bM[bJ] = bx;
									bB[bJ] = [];
									bQ = bx.length;
									while (bQ--) {
										bB[bJ][bQ] = (d[bQ] - bM[bJ][bQ]) / bH
									}
								}
								E[bJ] = d;
								break;
							default :
								d = [][bn](bU[bJ]);
								bx = [][bn](bM[bJ]);
								bB[bJ] = [];
								bQ = g.paper.customAttributes[bJ].length;
								while (bQ--) {
									bB[bJ][bQ] = ((d[bQ] || 0) - (bx[bQ] || 0))
											/ bH
								}
								break
						}
					}
				}
			}
			var bG = bU.easing, bO = aG.easing_formulas[bG];
			if (!bO) {
				bO = br(bG).match(c);
				if (bO && bO.length == 5) {
					var bC = bO;
					bO = function(bY) {
						return CubicBezierAtTime(bY, +bC[1], +bC[2], +bC[3],
								+bC[4], bH)
					}
				} else {
					bO = bk
				}
			}
			R = bU.start || bN.start || +new Date;
			bS = {
				anim : bN,
				percent : b,
				timestamp : R,
				start : R + (bN.del || 0),
				status : 0,
				initstatus : bL || 0,
				stop : false,
				ms : bH,
				easing : bO,
				from : bM,
				diff : bB,
				to : E,
				el : g,
				callback : bU.callback,
				prev : bE,
				next : bF,
				repeat : bz || bN.times,
				origin : g.attr(),
				totalOrigin : bv
			};
			V.push(bS);
			if (bL && !S && !by) {
				bS.stop = true;
				bS.start = new Date - bH * bL;
				if (V.length == 1) {
					return bl()
				}
			}
			if (by) {
				bS.start = new Date - bS.ms * bL
			}
			V.length == 1 && aC(bl)
		} else {
			S.initstatus = bL;
			S.start = new Date - S.ms * bL
		}
		u("drag.anim.start." + g.id, g, bN)
	}
	aG.animation = function(R, d, bv, S) {
		if (R instanceof bj) {
			return R
		}
		if (aG.is(bv, "function") || !bv) {
			S = S || bv || null;
			bv = null
		}
		R = Object(R);
		d = +d || 0;
		var E = {}, g, b;
		for (b in R) {
			if (R[ab](b) && ae(b) != b && ae(b) + "%" != b) {
				g = true;
				E[b] = R[b]
			}
		}
		if (!g) {
			return new bj(R, d)
		} else {
			bv && (E.easing = bv);
			S && (E.callback = S);
			return new bj({
						100 : E
					}, d)
		}
	};
	a0.animate = function(E, b, S, R) {
		var d = this;
		if (d.removed) {
			R && R.call(d);
			return d
		}
		var g = E instanceof bj ? E : aG.animation(E, b, S, R);
		aB(g, d, g.percents[0], null, d.attr());
		return d
	};
	a0.setTime = function(d, b) {
		if (d && b != null) {
			this.status(d, a4(b, d.ms) / d.ms)
		}
		return this
	};
	a0.status = function(R, E) {
		var d = [], g = 0, b, S;
		if (E != null) {
			aB(R, this, -1, a4(E, 1));
			return this
		} else {
			b = V.length;
			for (; g < b; g++) {
				S = V[g];
				if (S.el.id == this.id && (!R || S.anim == R)) {
					if (R) {
						return S.status
					}
					d.push({
								anim : S.anim,
								status : S.status
							})
				}
			}
			if (R) {
				return 0
			}
			return d
		}
	};
	a0.pause = function(d) {
		for (var b = 0; b < V.length; b++) {
			if (V[b].el.id == this.id && (!d || V[b].anim == d)) {
				if (u("drag.anim.pause." + this.id, this, V[b].anim) !== false) {
					V[b].paused = true
				}
			}
		}
		return this
	};
	a0.resume = function(d) {
		for (var b = 0; b < V.length; b++) {
			if (V[b].el.id == this.id && (!d || V[b].anim == d)) {
				var g = V[b];
				if (u("drag.anim.resume." + this.id, this, g.anim) !== false) {
					delete g.paused;
					this.status(g.anim, g.status)
				}
			}
		}
		return this
	};
	a0.stop = function(d) {
		for (var b = 0; b < V.length; b++) {
			if (V[b].el.id == this.id && (!d || V[b].anim == d)) {
				if (u("drag.anim.stop." + this.id, this, V[b].anim) !== false) {
					V.splice(b--, 1)
				}
			}
		}
		return this
	};
	function U(d) {
		for (var b = 0; b < V.length; b++) {
			if (V[b].el.paper == d) {
				V.splice(b--, 1)
			}
		}
	}
	u.on("drag.remove", U);
	u.on("drag.clear", U);
	a0.toString = function() {
		return "Rapha\xebl\u2019s object"
	};
	var ac = function(b) {
		this.items = [];
		this.length = 0;
		this.type = "set";
		if (b) {
			for (var d = 0, g = b.length; d < g; d++) {
				if (b[d]
						&& (b[d].constructor == a0.constructor || b[d].constructor == ac)) {
					this[this.items.length] = this.items[this.items.length] = b[d];
					this.length++
				}
			}
		}
	}, aV = ac.prototype;
	aV.push = function() {
		var E, b;
		for (var d = 0, g = arguments.length; d < g; d++) {
			E = arguments[d];
			if (E && (E.constructor == a0.constructor || E.constructor == ac)) {
				b = this.items.length;
				this[b] = this.items[b] = E;
				this.length++
			}
		}
		return this
	};
	aV.pop = function() {
		this.length && delete this[this.length--];
		return this.items.pop()
	};
	aV.forEach = function(E, b) {
		for (var d = 0, g = this.items.length; d < g; d++) {
			if (E.call(b, this.items[d], d) === false) {
				return this
			}
		}
		return this
	};
	for (var y in a0) {
		if (a0[ab](y)) {
			aV[y] = (function(b) {
				return function() {
					var d = arguments;
					return this.forEach(function(g) {
								g[b][bq](g, d)
							})
				}
			})(y)
		}
	}
	aV.attr = function(d, S) {
		if (d && aG.is(d, aW) && aG.is(d[0], "object")) {
			for (var b = 0, R = d.length; b < R; b++) {
				this.items[b].attr(d[b])
			}
		} else {
			for (var g = 0, E = this.items.length; g < E; g++) {
				this.items[g].attr(d, S)
			}
		}
		return this
	};
	aV.clear = function() {
		while (this.length) {
			this.pop()
		}
	};
	aV.splice = function(E, bv, bw) {
		E = E < 0 ? l(this.length + E, 0) : E;
		bv = l(0, a4(this.length - E, bv));
		var g = [], b = [], d = [], R;
		for (R = 2; R < arguments.length; R++) {
			d.push(arguments[R])
		}
		for (R = 0; R < bv; R++) {
			b.push(this[E + R])
		}
		for (; R < this.length - E; R++) {
			g.push(this[E + R])
		}
		var S = d.length;
		for (R = 0; R < S + g.length; R++) {
			this.items[E + R] = this[E + R] = R < S ? d[R] : g[R - S]
		}
		R = this.items.length = this.length -= bv - S;
		while (this[R]) {
			delete this[R++]
		}
		return new ac(b)
	};
	aV.exclude = function(g) {
		for (var b = 0, d = this.length; b < d; b++) {
			if (this[b] == g) {
				this.splice(b, 1);
				return true
			}
		}
	};
	aV.animate = function(g, b, bv, bx) {
		(aG.is(bv, "function") || !bv) && (bx = bv || null);
		var S = this.items.length, E = S, by, bw = this, R;
		if (!S) {
			return this
		}
		bx && (R = function() {
			!--S && bx.call(bw)
		});
		bv = aG.is(bv, aa) ? bv : R;
		var d = aG.animation(g, b, bv, R);
		by = this.items[--E].animate(d);
		while (E--) {
			this.items[E] && !this.items[E].removed
					&& this.items[E].animateWith(by, d, d);
			(this.items[E] && !this.items[E].removed) || S--
		}
		return this
	};
	aV.insertAfter = function(d) {
		var b = this.items.length;
		while (b--) {
			this.items[b].insertAfter(d)
		}
		return this
	};
	aG.registerFont = function(d) {
		if (!d.face) {
			return d
		}
		this.fonts = this.fonts || {};
		var E = {
			w : d.w,
			face : {},
			glyphs : {}
		}, g = d.face["font-family"];
		for (var bv in d.face) {
			if (d.face[ab](bv)) {
				E.face[bv] = d.face[bv]
			}
		}
		if (this.fonts[g]) {
			this.fonts[g].push(E)
		} else {
			this.fonts[g] = [E]
		}
		if (!d.svg) {
			E.face["units-per-em"] = M(d.face["units-per-em"], 10);
			for (var R in d.glyphs) {
				if (d.glyphs[ab](R)) {
					var S = d.glyphs[R];
					E.glyphs[R] = {
						w : S.w,
						k : {},
						d : S.d && "M"
								+ S.d.replace(/[mlcxtrv]/g, function(bw) {
											return {
												l : "L",
												c : "C",
												x : "z",
												t : "m",
												r : "l",
												v : "c"
											}[bw] || "M"
										}) + "z"
					};
					if (S.k) {
						for (var b in S.k) {
							if (S[ab](b)) {
								E.glyphs[R].k[b] = S.k[b]
							}
						}
					}
				}
			}
		}
		return d
	};
	aR.getFont = function(bw, bx, d, E) {
		E = E || "normal";
		d = d || "normal";
		bx = +bx || {
			normal : 400,
			bold : 700,
			lighter : 300,
			bolder : 800
		}[bx] || 400;
		if (!aG.fonts) {
			return
		}
		var R = aG.fonts[bw];
		if (!R) {
			var g = new RegExp("(^|\\s)" + bw.replace(/[^\w\d\s+!~.:_-]/g, aL)
							+ "(\\s|$)", "i");
			for (var b in aG.fonts) {
				if (aG.fonts[ab](b)) {
					if (g.test(b)) {
						R = aG.fonts[b];
						break
					}
				}
			}
		}
		var S;
		if (R) {
			for (var bv = 0, by = R.length; bv < by; bv++) {
				S = R[bv];
				if (S.face["font-weight"] == bx
						&& (S.face["font-style"] == d || !S.face["font-style"])
						&& S.face["font-stretch"] == E) {
					break
				}
			}
		}
		return S
	};
	aR.print = function(bw, bv, b, bz, bB, bJ, g, d) {
		bJ = bJ || "middle";
		g = l(a4(g || 0, 1), -1);
		d = l(a4(d || 1, 3), 1);
		var bI = br(b)[z](aL), bF = 0, bH = 0, bD = aL, bK;
		aG.is(bz, "string") && (bz = this.getFont(bz));
		if (bz) {
			bK = (bB || 16) / bz.face["units-per-em"];
			var R = bz.face.bbox[z](a), by = +R[0], E = R[3] - R[1], S = 0, bA = +R[1]
					+ (bJ == "baseline" ? E + (+bz.face.descent) : E / 2);
			for (var bE = 0, bx = bI.length; bE < bx; bE++) {
				if (bI[bE] == "\n") {
					bF = 0;
					bG = 0;
					bH = 0;
					S += E * d
				} else {
					var bC = bH && bz.glyphs[bI[bE - 1]] || {}, bG = bz.glyphs[bI[bE]];
					bF += bH ? (bC.w || bz.w) + (bC.k && bC.k[bI[bE]] || 0)
							+ (bz.w * g) : 0;
					bH = 1
				}
				if (bG && bG.d) {
					bD += aG.transformPath(bG.d,
							["t", bF * bK, S * bK, "s", bK, bK, by, bA, "t",
									(bw - by) / bK, (bv - bA) / bK])
				}
			}
		}
		return this.path(bD).attr({
					fill : "#000",
					stroke : "none"
				})
	};
	aR.add = function(E) {
		if (aG.is(E, "array")) {
			var g = this.set(), d = 0, R = E.length, b;
			for (; d < R; d++) {
				b = E[d] || {};
				bf[ab](b.type) && g.push(this[b.type]().attr(b))
			}
		}
		return g
	};
	aG.format = function(d, g) {
		var b = aG.is(g, aW) ? [0][bn](g) : arguments;
		d && aG.is(d, aa) && b.length - 1 && (d = d.replace(a9, function(R, E) {
					return b[++E] == null ? aL : b[E]
				}));
		return d || aL
	};
	aG.fullfill = (function() {
		var g = /\{([^\}]+)\}/g, b = /(?:(?:^|\.)(.+?)(?=\[|\.|$|\()|\[('|")(.+?)\2\])(\(\))?/g, d = function(
				S, R, bv) {
			var E = bv;
			R.replace(b, function(by, bx, bw, bA, bz) {
						bx = bx || bA;
						if (E) {
							if (bx in E) {
								E = E[bx]
							}
							typeof E == "function" && bz && (E = E())
						}
					});
			E = (E == null || E == bv ? S : E) + "";
			return E
		};
		return function(R, E) {
			return String(R).replace(g, function(bv, S) {
						return d(bv, S, E)
					})
		}
	})();
	aG.ninja = function() {
		q.was ? (ao.win.Drag = q.is) : delete Drag;
		return aG
	};
	aG.st = aV;
(function(E, d, g) {
		if (E.readyState == null && E.addEventListener) {
			E.addEventListener(d, g = function() {
						E.removeEventListener(d, g, false);
						E.readyState = "complete"
					}, false);
			E.readyState = "loading"
		}
		function b() {
			(/in/).test(E.readyState) ? setTimeout(b, 9) : aG
					.eve("drag.DOMload")
		}
		b()
	})(document, "DOMContentLoaded");
	u.on("drag.DOMload", function() {
				af = true
			});
(function() {
		if (!aG.svg) {
			return
		}
		var g = "hasOwnProperty", bQ = String, bC = parseFloat, bF = parseInt, bv = Math, bR = bv.max, bH = bv.abs, bx = bv.pow, bw = /[, ]+/, bO = aG.eve, bG = "", bz = " ";
		var bD = "http://www.w3.org/1999/xlink", bN = {
			block : "M5,0 0,2.5 5,5z",
			classic : "M5,0 0,2.5 5,5 3.5,3 3.5,2z",
			diamond : "M2.5,0 5,2.5 2.5,5 0,2.5z",
			open : "M6,1 1,3.5 6,6",
			oval : "M2.5,0A2.5,2.5,0,0,1,2.5,5 2.5,2.5,0,0,1,2.5,0z"
		}, bJ = {};
		aG.toString = function() {
			return "Your browser supports SVG.\nYou are running Rapha\xebl "
					+ this.version
		};
		var by = function(bS, E) {
			if (E) {
				if (typeof bS == "string") {
					bS = by(bS)
				}
				for (var S in E) {
					if (E[g](S)) {
						if (S.substring(0, 6) == "xlink:") {
							bS.setAttributeNS(bD, S.substring(6), bQ(E[S]))
						} else {
							bS.setAttribute(S, bQ(E[S]))
						}
					}
				}
			} else {
				bS = aG._g.doc
						.createElementNS("http://www.w3.org/2000/svg", bS);
				bS.style
						&& (bS.style.webkitTapHighlightColor = "rgba(0,0,0,0)")
			}
			return bS
		}, b = function(bZ, b3) {
			var b1 = "linear", S = bZ.id + b3, bX = 0.5, bV = 0.5, bT = bZ.node, E = bZ.paper, b5 = bT.style, bS = aG._g.doc
					.getElementById(S);
			if (!bS) {
				b3 = bQ(b3).replace(aG._radial_gradient, function(b8, b6, b9) {
					b1 = "radial";
					if (b6 && b9) {
						bX = bC(b6);
						bV = bC(b9);
						var b7 = ((bV > 0.5) * 2 - 1);
						bx(bX - 0.5, 2) + bx(bV - 0.5, 2) > 0.25
								&& (bV = bv.sqrt(0.25 - bx(bX - 0.5, 2)) * b7
										+ 0.5) && bV != 0.5
								&& (bV = bV.toFixed(5) - 0.00001 * b7)
					}
					return bG
				});
				b3 = b3.split(/\s*\-\s*/);
				if (b1 == "linear") {
					var bW = b3.shift();
					bW = -bC(bW);
					if (isNaN(bW)) {
						return null
					}
					var bU = [0, 0, bv.cos(aG.rad(bW)), bv.sin(aG.rad(bW))], b2 = 1
							/ (bR(bH(bU[2]), bH(bU[3])) || 1);
					bU[2] *= b2;
					bU[3] *= b2;
					if (bU[2] < 0) {
						bU[0] = -bU[2];
						bU[2] = 0
					}
					if (bU[3] < 0) {
						bU[1] = -bU[3];
						bU[3] = 0
					}
				}
				var b0 = aG._parseDots(b3);
				if (!b0) {
					return null
				}
				S = S.replace(/[\(\)\s,\xb0#]/g, "_");
				if (bZ.gradient && S != bZ.gradient.id) {
					E.defs.removeChild(bZ.gradient);
					delete bZ.gradient
				}
				if (!bZ.gradient) {
					bS = by(b1 + "Gradient", {
								id : S
							});
					bZ.gradient = bS;
					by(bS,	b1 == "radial" ? {
								fx : bX,
								fy : bV
							} : {
								x1 : bU[0],
								y1 : bU[1],
								x2 : bU[2],
								y2 : bU[3],
								gradientTransform : bZ.matrix.invert()
							});
					E.defs.appendChild(bS);
					for (var bY = 0, b4 = b0.length; bY < b4; bY++) {
						bS.appendChild(by("stop", {
									offset : b0[bY].offset ? b0[bY].offset : bY
											? "100%"
											: "0%",
									"stop-color" : b0[bY].color || "#fff"
								}))
					}
				}
			}
			by(bT, {
						fill : "url(#" + S + ")",
						opacity : 1,
						"fill-opacity" : 1
					});
			b5.fill = bG;
			b5.opacity = 1;
			b5.fillOpacity = 1;
			return 1
		}, d = function(S) {
			var E = S.getBBox(1);
			by(S.pattern, {
						patternTransform : S.matrix.invert() + " translate("
								+ E.x + "," + E.y + ")"
					})
		}, bK = {
			"" : [0],
			none : [0],
			"-" : [3, 1],
			"." : [1, 1],
			"-." : [3, 1, 1, 1],
			"-.." : [3, 1, 1, 1, 1, 1],
			". " : [1, 3],
			"- " : [4, 3],
			"--" : [8, 3],
			"- ." : [4, 3, 1, 3],
			"--." : [8, 3, 1, 3],
			"--.." : [8, 3, 1, 3, 1, 3]
		}, bA = function(bW, bU, bV) {
			bU = bK[bQ(bU).toLowerCase()];
			if (bU) {
				var bS = bW.attrs["stroke-width"] || "1", E = {
					round : bS,
					square : bS,
					butt : 0
				}[bW.attrs["stroke-linecap"] || bV["stroke-linecap"]] || 0, bT = [], S = bU.length;
				while (S--) {
					bT[S] = bU[S] * bS + ((S % 2) ? 1 : -1) * E
				}
				by(bW.node, {
							"stroke-dasharray" : bT.join(",")
						})
			}
		}, bL = function(b1, b9) {
			var b5 = b1.node, b2 = b1.attrs, bZ = b5.style.visibility;
			b5.style.visibility = "hidden";
			for (var b4 in b9) {
				if (b9[g](b4)) {
					if (!aG._availableAttrs[g](b4)) {
						continue
					}
					var b3 = b9[b4];
					b2[b4] = b3;
					switch (b4) {
						case "blur" :
							b1.blur(b3);
							break;
						case "title" :
							var ca = b5.getElementsByTagName("title");
							if (ca.length && (ca = ca[0])) {
								ca.firstChild.nodeValue = b3
							} else {
								ca = by("title");
								var cb = aG._g.doc.createTextNode(b3);
								ca.appendChild(cb);
								b5.appendChild(ca)
							}
							break;
						case "href" :
						case "target" :
							var b7 = b5.parentNode;
							if (b7.tagName.toLowerCase() != "a") {
								var bU = by("a");
								b7.insertBefore(bU, b5);
								bU.appendChild(b5);
								b7 = bU
							}
							if (b4 == "target") {
								b7.setAttributeNS(bD, "show", b3 == "blank"
												? "new"
												: b3)
							} else {
								b7.setAttributeNS(bD, b4, b3)
							}
							break;
						case "cursor" :
							b5.style.cursor = b3;
							break;
						case "transform" :
							b1.transform(b3);
							break;
						case "arrow-start" :
							addArrow(b1, b3);
							break;
						case "arrow-end" :
							addArrow(b1, b3, 1);
							break;
						case "clip-rect" :
							var S = bQ(b3).split(bw);
							if (S.length == 4) {
								b1.clip
										&& b1.clip.parentNode.parentNode
												.removeChild(b1.clip.parentNode);
								var bS = by("clipPath"), b6 = by("rect");
								bS.id = aG.createUUID();
								by(b6, {
											x : S[0],
											y : S[1],
											width : S[2],
											height : S[3]
										});
								bS.appendChild(b6);
								b1.paper.defs.appendChild(bS);
								by(b5, {
											"clip-path" : "url(#" + bS.id + ")"
										});
								b1.clip = b6
							}
							if (!b3) {
								var b0 = b5.getAttribute("clip-path");
								if (b0) {
									var b8 = aG._g.doc.getElementById(b0
											.replace(/(^url\(#|\)$)/g, bG));
									b8 && b8.parentNode.removeChild(b8);
									by(b5, {
												"clip-path" : bG
											});
									delete b1.clip
								}
							}
							break;
						case "path" :
							if (b1.type == "path") {
								by(b5, {
											d : b3
													? b2.path = aG
															._pathToAbsolute(b3)
													: "M0,0"
										});
								b1._.dirty = 1;
								if (b1._.arrows) {
									"startString" in b1._.arrows
											&& addArrow(b1,
													b1._.arrows.startString);
									"endString" in b1._.arrows
											&& addArrow(b1,
													b1._.arrows.endString, 1)
								}
							}
							break;
						case "width" :
							b5.setAttribute(b4, b3);
							b1._.dirty = 1;
							if (b2.fx) {
								b4 = "x";
								b3 = b2.x
							} else {
								break
							}
						case "x" :
							if (b2.fx) {
								b3 = -b2.x - (b2.width || 0)
							}
						case "rx" :
							if (b4 == "rx" && b1.type == "rect") {
								break
							}
						case "cx" :
							b5.setAttribute(b4, b3);
							b1.pattern && d(b1);
							b1._.dirty = 1;
							break;
						case "height" :
							b5.setAttribute(b4, b3);
							b1._.dirty = 1;
							if (b2.fy) {
								b4 = "y";
								b3 = b2.y
							} else {
								break
							}
						case "y" :
							if (b2.fy) {
								b3 = -b2.y - (b2.height || 0)
							}
						case "ry" :
							if (b4 == "ry" && b1.type == "rect") {
								break
							}
						case "cy" :
							b5.setAttribute(b4, b3);
							b1.pattern && d(b1);
							b1._.dirty = 1;
							break;
						case "r" :
							if (b1.type == "rect") {
								by(b5, {
											rx : b3,
											ry : b3
										})
							} else {
								b5.setAttribute(b4, b3)
							}
							b1._.dirty = 1;
							break;
						case "src" :
							if (b1.type == "image") {
								b5.setAttributeNS(bD, "href", b3)
							}
							break;
						case "stroke-width" :
							if (b1._.sx != 1 || b1._.sy != 1) {
								b3 /= bR(bH(b1._.sx), bH(b1._.sy)) || 1
							}
							if (b1.paper._vbSize) {
								b3 *= b1.paper._vbSize
							}
							b5.setAttribute(b4, b3);
							if (b2["stroke-dasharray"]) {
								bA(b1, b2["stroke-dasharray"], b9)
							}
							if (b1._.arrows) {
								"startString" in b1._.arrows
										&& addArrow(b1, b1._.arrows.startString);
								"endString" in b1._.arrows
										&& addArrow(b1, b1._.arrows.endString,
												1)
							}
							break;
						case "stroke-dasharray" :
							bA(b1, b3, b9);
							break;
						case "fill" :
							var bV = bQ(b3).match(aG._ISURL);
							if (bV) {
								bS = by("pattern");
								var bY = by("image");
								bS.id = aG.createUUID();
								by(bS, {
											x : 0,
											y : 0,
											patternUnits : "userSpaceOnUse",
											height : 1,
											width : 1
										});
								by(bY, {
											x : 0,
											y : 0,
											"xlink:href" : bV[1]
										});
								bS.appendChild(bY);
								(function(cc) {
									aG._preload(bV[1], function() {
										var cd = this.offsetWidth, ce = this.offsetHeight;
										by(cc, {
													width : cd,
													height : ce
												});
										by(bY, {
													width : cd,
													height : ce
												});
										b1.paper.safari()
									})
								})(bS);
								b1.paper.defs.appendChild(bS);
								by(b5, {
											fill : "url(#" + bS.id + ")"
										});
								b1.pattern = bS;
								b1.pattern && d(b1);
								break
							}
							var bT = aG.getRGB(b3);
							if (!bT.error) {
								delete b9.gradient;
								delete b2.gradient;
								!aG.is(b2.opacity, "undefined")
										&& aG.is(b9.opacity, "undefined")
										&& by(b5, {
													opacity : b2.opacity
												});
								!aG.is(b2["fill-opacity"], "undefined")
										&& aG.is(b9["fill-opacity"],
												"undefined") && by(b5, {
													"fill-opacity" : b2["fill-opacity"]
												})
							} else {
								if ((b1.type == "circle"
										|| b1.type == "ellipse" || bQ(b3)
										.charAt() != "r")
										&& b(b1, b3)) {
									if ("opacity" in b2 || "fill-opacity" in b2) {
										var E = aG._g.doc.getElementById(b5
												.getAttribute("fill").replace(
														/^url\(#|\)$/g, bG));
										if (E) {
											var bW = E
													.getElementsByTagName("stop");
											by(bW[bW.length - 1], {
												"stop-opacity" : ("opacity" in b2
														? b2.opacity
														: 1)
														* ("fill-opacity" in b2
																? b2["fill-opacity"]
																: 1)
											})
										}
									}
									b2.gradient = b3;
									b2.fill = "none";
									break
								}
							}
							bT[g]("opacity") && by(b5, {
										"fill-opacity" : bT.opacity > 1
												? bT.opacity / 100
												: bT.opacity
									});
						case "stroke" :
							bT = aG.getRGB(b3);
							b5.setAttribute(b4, bT.hex);
							b4 == "stroke" && bT[g]("opacity") && by(b5, {
										"stroke-opacity" : bT.opacity > 1
												? bT.opacity / 100
												: bT.opacity
									});
							if (b4 == "stroke" && b1._.arrows) {
								"startString" in b1._.arrows
										&& addArrow(b1, b1._.arrows.startString);
								"endString" in b1._.arrows
										&& addArrow(b1, b1._.arrows.endString,
												1)
							}
							break;
						case "gradient" :
							(b1.type == "circle" || b1.type == "ellipse" || bQ(b3)
									.charAt() != "r")
									&& b(b1, b3);
							break;
						case "opacity" :
							if (b2.gradient && !b2[g]("stroke-opacity")) {
								by(b5, {
											"stroke-opacity" : b3 > 1 ? b3
													/ 100 : b3
										})
							}
						case "fill-opacity" :
							if (b2.gradient) {
								E = aG._g.doc.getElementById(b5
										.getAttribute("fill").replace(
												/^url\(#|\)$/g, bG));
								if (E) {
									bW = E.getElementsByTagName("stop");
									by(bW[bW.length - 1], {
												"stop-opacity" : b3
											})
								}
								break
							}
						default :
							b4 == "font-size" && (b3 = bF(b3, 10) + "px");
							var bX = b4.replace(/(\-.)/g, function(cc) {
										return cc.substring(1).toUpperCase()
									});
							b5.style[bX] = b3;
							b1._.dirty = 1;
							b5.setAttribute(b4, b3);
							break
					}
				}
			}
			bE(b1, b9);
			b5.style.visibility = bZ
		}, bP = 1.2, bE = function(E, bU) {
			if (E.type != "text"
					|| !(bU[g]("text") || bU[g]("font") || bU[g]("font-size")
							|| bU[g]("x") || bU[g]("y"))) {
				return
			}
			var bZ = E.attrs, bS = E.node, b1 = bS.firstChild ? bF(
					aG._g.doc.defaultView.getComputedStyle(bS.firstChild, bG)
							.getPropertyValue("font-size"), 10) : 10;
			if (bU[g]("text")) {
				bZ.text = bU.text;
				while (bS.firstChild) {
					bS.removeChild(bS.firstChild)
				}
				var bT = bQ(bU.text).split("\n"), S = [], bX;
				for (var bV = 0, b0 = bT.length; bV < b0; bV++) {
					bX = by("tspan");
					bV && by(bX, {
								dy : b1 * bP,
								x : bZ.x
							});
					bX.appendChild(aG._g.doc.createTextNode(bT[bV]));
					bS.appendChild(bX);
					S[bV] = bX
				}
			} else {
				S = bS.getElementsByTagName("tspan");
				for (bV = 0, b0 = S.length; bV < b0; bV++) {
					if (bV) {
						by(S[bV], {
									dy : b1 * bP,
									x : bZ.x
								})
					} else {
						by(S[0], {
									dy : 0
								})
					}
				}
			}
			by(bS, {
						x : bZ.x,
						y : bZ.y
					});
			E._.dirty = 1;
			var bW = E._getBBox(), bY = bZ.y - (bW.y + bW.height / 2);
			bY && aG.is(bY, "finite") && by(S[0], {
						dy : bY
					})
		}, bI = function(S, E) {
			var bT = 0, bS = 0;
			this[0] = this.node = S;
			S.drag = true;
			this.id = aG._oid++;
			S.dragid = this.id;
			this.matrix = aG.matrix();
			this.realPath = null;
			this.paper = E;
			this.attrs = this.attrs || {};
			this._ = {
				transform : [],
				sx : 1,
				sy : 1,
				deg : 0,
				dx : 0,
				dy : 0,
				dirty : 1
			};
			!E.bottom && (E.bottom = this);
			this.prev = E.top;
			E.top && (E.top.next = this);
			E.top = this;
			this.next = null
		}, bB = aG.el;
		bI.prototype = bB;
		bB.constructor = bI;
		aG._engine.path = function(E, bT) {
			var S = by("path");
			bT.canvas && bT.canvas.appendChild(S);
			var bS = new bI(S, bT);
			bS.type = "path";
			bL(bS, {
						fill : "none",
						stroke : "#000",
						path : E
					});
			return bS
		};
		bB.rotate = function(S, E, bT) {
			if (this.removed) {
				return this
			}
			S = bQ(S).split(bw);
			if (S.length - 1) {
				E = bC(S[1]);
				bT = bC(S[2])
			}
			S = bC(S[0]);
			(bT == null) && (E = bT);
			if (E == null || bT == null) {
				var bS = this.getBBox(1);
				E = bS.x + bS.width / 2;
				bT = bS.y + bS.height / 2
			}
			this.transform(this._.transform.concat([["r", S, E, bT]]));
			return this
		};
		bB.scale = function(bU, bS, E, bT) {
			if (this.removed) {
				return this
			}
			bU = bQ(bU).split(bw);
			if (bU.length - 1) {
				bS = bC(bU[1]);
				E = bC(bU[2]);
				bT = bC(bU[3])
			}
			bU = bC(bU[0]);
			(bS == null) && (bS = bU);
			(bT == null) && (E = bT);
			if (E == null || bT == null) {
				var S = this.getBBox(1)
			}
			E = E == null ? S.x + S.width / 2 : E;
			bT = bT == null ? S.y + S.height / 2 : bT;
			this.transform(this._.transform.concat([["s", bU, bS, E, bT]]));
			return this
		};
		bB.translate = function(S, E) {
			if (this.removed) {
				return this
			}
			S = bQ(S).split(bw);
			if (S.length - 1) {
				E = bC(S[1])
			}
			S = bC(S[0]) || 0;
			E = +E || 0;
			this.transform(this._.transform.concat([["t", S, E]]));
			return this
		};
		bB.transform = function(S) {
			var bS = this._;
			if (S == null) {
				return bS.transform
			}
			aG._extractTransform(this, S);
			this.clip && by(this.clip, {
						transform : this.matrix.invert()
					});
			this.pattern && d(this);
			this.node && by(this.node, {
						transform : this.matrix
					});
			if (bS.sx != 1 || bS.sy != 1) {
				var E = this.attrs[g]("stroke-width")
						? this.attrs["stroke-width"]
						: 1;
				this.attr({
							"stroke-width" : E
						})
			}
			return this
		};
		bB.hide = function() {
			!this.removed
					&& this.paper.safari(this.node.style.display = "none");
			return this
		};
		bB.show = function() {
			!this.removed && this.paper.safari(this.node.style.display = "");
			return this
		};
		bB.remove = function() {
			if (this.removed || !this.node.parentNode) {
				return
			}
			var S = this.paper;
			S.__set__ && S.__set__.exclude(this);
			bO.unbind("drag.*.*." + this.id);
			if (this.gradient) {
				S.defs.removeChild(this.gradient)
			}
			aG._tear(this, S);
			if (this.node.parentNode.tagName.toLowerCase() == "a") {
				this.node.parentNode.parentNode
						.removeChild(this.node.parentNode)
			} else {
				this.node.parentNode.removeChild(this.node)
			}
			for (var E in this) {
				this[E] = typeof this[E] == "function"
						? aG._removedFactory(E)
						: null
			}
			this.removed = true
		};
		bB._getBBox = function() {
			if (this.node.style.display == "none") {
				this.show();
				var E = true
			}
			var bS = {};
			try {
				bS = this.node.getBBox()
			} catch (S) {
			} finally {
				bS = bS || {}
			}
			E && this.hide();
			return bS
		};
		bB.attr = function(E, bZ) {
			if (this.removed) {
				return this
			}
			if (E == null) {
				var bW = {};
				for (var bY in this.attrs) {
					if (this.attrs[g](bY)) {
						bW[bY] = this.attrs[bY]
					}
				}
				bW.gradient && bW.fill == "none" && (bW.fill = bW.gradient)
						&& delete bW.gradient;
				bW.transform = this._.transform;
				return bW
			}
			if (bZ == null && aG.is(E, "string")) {
				if (E == "fill" && this.attrs.fill == "none"
						&& this.attrs.gradient) {
					return this.attrs.gradient
				}
				if (E == "transform") {
					return this._.transform
				}
				var bX = E.split(bw), bT = {};
				for (var bU = 0, b1 = bX.length; bU < b1; bU++) {
					E = bX[bU];
					if (E in this.attrs) {
						bT[E] = this.attrs[E]
					} else {
						if (aG.is(this.paper.customAttributes[E], "function")) {
							bT[E] = this.paper.customAttributes[E].def
						} else {
							bT[E] = aG._availableAttrs[E]
						}
					}
				}
				return b1 - 1 ? bT : bT[bX[0]]
			}
			if (bZ == null && aG.is(E, "array")) {
				bT = {};
				for (bU = 0, b1 = E.length; bU < b1; bU++) {
					bT[E[bU]] = this.attr(E[bU])
				}
				return bT
			}
			if (bZ != null) {
				var S = {};
				S[E] = bZ
			} else {
				if (E != null && aG.is(E, "object")) {
					S = E
				}
			}
			for (var b0 in S) {
				bO("drag.attr." + b0 + "." + this.id, this, S[b0])
			}
			for (b0 in this.paper.customAttributes) {
				if (this.paper.customAttributes[g](b0) && S[g](b0)
						&& aG.is(this.paper.customAttributes[b0], "function")) {
					var bV = this.paper.customAttributes[b0].apply(this, []
									.concat(S[b0]));
					this.attrs[b0] = S[b0];
					for (var bS in bV) {
						if (bV[g](bS)) {
							S[bS] = bV[bS]
						}
					}
				}
			}
			bL(this, S);
			return this
		};
		bB.insertAfter = function(E) {
			if (this.removed) {
				return this
			}
			var S = E.node || E[E.length - 1].node;
			if (S.nextSibling) {
				S.parentNode.insertBefore(this.node, S.nextSibling)
			} else {
				S.parentNode.appendChild(this.node)
			}
			aG._insertafter(this, E, this.paper);
			return this
		};
		bB.insertBefore = function(E) {
			if (this.removed) {
				return this
			}
			var S = E.node || E[0].node;
			S.parentNode.insertBefore(this.node, S);
			aG._insertbefore(this, E, this.paper);
			return this
		};
		bB.blur = function(S) {
			var E = this;
			if (+S !== 0) {
				var bS = by("filter"), bT = by("feGaussianBlur");
				E.attrs.blur = S;
				bS.id = aG.createUUID();
				by(bT, {
							stdDeviation : +S || 1.5
						});
				bS.appendChild(bT);
				E.paper.defs.appendChild(bS);
				E._blur = bS;
				by(E.node, {
							filter : "url(#" + bS.id + ")"
						})
			} else {
				if (E._blur) {
					E._blur.parentNode.removeChild(E._blur);
					delete E._blur;
					delete E.attrs.blur
				}
				E.node.removeAttribute("filter")
			}
			return E
		};
		aG._engine.circle = function(S, E, bV, bU) {
			var bT = by("circle");
			S.canvas && S.canvas.appendChild(bT);
			var bS = new bI(bT, S);
			bS.attrs = {
				cx : E,
				cy : bV,
				r : bU,
				fill : "none",
				stroke : "#000"
			};
			bS.type = "circle";
			by(bT, bS.attrs);
			return bS
		};
		aG._engine.rect = function(bS, E, bX, S, bV, bW) {
			var bU = by("rect");
			bS.canvas && bS.canvas.appendChild(bU);
			var bT = new bI(bU, bS);
			bT.attrs = {
				x : E,
				y : bX,
				width : S,
				height : bV,
				r : bW || 0,
				rx : bW || 0,
				ry : bW || 0,
				fill : "none",
				stroke : "#000"
			};
			bT.type = "rect";
			by(bU, bT.attrs);
			return bT
		};
		aG._engine.ellipse = function(S, E, bW, bV, bU) {
			var bT = by("ellipse");
			S.canvas && S.canvas.appendChild(bT);
			var bS = new bI(bT, S);
			bS.attrs = {
				cx : E,
				cy : bW,
				rx : bV,
				ry : bU,
				fill : "none",
				stroke : "#000"
			};
			bS.type = "ellipse";
			by(bT, bS.attrs);
			return bS
		};
		aG._engine.image = function(bS, bW, E, bX, S, bV) {
			var bU = by("image");
			by(bU, {
						x : E,
						y : bX,
						width : S,
						height : bV,
						preserveAspectRatio : "none"
					});
			bU.setAttributeNS(bD, "href", bW);
			bS.canvas && bS.canvas.appendChild(bU);
			var bT = new bI(bU, bS);
			bT.attrs = {
				x : E,
				y : bX,
				width : S,
				height : bV,
				src : bW
			};
			bT.type = "image";
			return bT
		};
		aG._engine.text = function(S, E, bV, bU) {
			var bT = by("text");
			S.canvas && S.canvas.appendChild(bT);
			var bS = new bI(bT, S);
			bS.attrs = {
				x : E,
				y : bV,
				"text-anchor" : "middle",
				text : bU,
				font : aG._availableAttrs.font,
				stroke : "none",
				fill : "#000"
			};
			bS.type = "text";
			bL(bS, bS.attrs);
			return bS
		};
		aG._engine.setSize = function(S, E) {
			this.width = S || this.width;
			this.height = E || this.height;
			this.canvas.setAttribute("width", this.width);
			this.canvas.setAttribute("height", this.height);
			if (this._viewBox) {
				this.setViewBox.apply(this, this._viewBox)
			}
			return this
		};
		aG._engine.create = function() {
			var bT = aG._getContainer.apply(0, arguments), S = bT
					&& bT.container, bX = bT.x, bW = bT.y, bS = bT.width, bY = bT.height;
			if (!S) {
				throw new Error("SVG container not found.")
			}
			var E = by("svg"), bV = "overflow:hidden;", bU;
			bX = bX || 0;
			bW = bW || 0;
			bS = bS || 512;
			bY = bY || 342;
			by(E, {
						height : bY,
						version : 1.1,
						width : bS,
						xmlns : "http://www.w3.org/2000/svg"
					});
			if (S == 1) {
				E.style.cssText = bV + "position:absolute;left:" + bX
						+ "px;top:" + bW + "px";
				aG._g.doc.body.appendChild(E);
				bU = 1
			} else {
				E.style.cssText = bV + "position:relative";
				if (S.firstChild) {
					S.insertBefore(E, S.firstChild)
				} else {
					S.appendChild(E)
				}
			}
			S = new aG._Paper;
			S.width = bS;
			S.height = bY;
			S.canvas = E;
			S.clear();
			S._left = S._top = 0;
			bU && (S.renderfix = function() {
			});
			S.renderfix();
			return S
		};
		aG.prototype.renderfix = function() {
			var bV = this.canvas, E = bV.style, bU;
			try {
				bU = bV.getScreenCTM() || bV.createSVGMatrix()
			} catch (bT) {
				bU = bV.createSVGMatrix()
			}
			var bS = -bU.e % 1, S = -bU.f % 1;
			if (bS || S) {
				if (bS) {
					this._left = (this._left + bS) % 1;
					E.left = this._left + "px"
				}
				if (S) {
					this._top = (this._top + S) % 1;
					E.top = this._top + "px"
				}
			}
		};
		aG.prototype.clear = function() {
			aG.eve("drag.clear", this);
			var E = this.canvas;
			while (E.firstChild) {
				E.removeChild(E.firstChild)
			}
			this.bottom = this.top = null;
			(this.desc = by("desc")).appendChild(aG._g.doc
					.createTextNode("Created By Liyu"));
			E.appendChild(this.desc);
			E.appendChild(this.defs = by("defs"))
		};
		aG.prototype.remove = function() {
			bO("drag.remove", this);
			this.canvas.parentNode
					&& this.canvas.parentNode.removeChild(this.canvas);
			for (var E in this) {
				this[E] = typeof this[E] == "function"
						? aG._removedFactory(E)
						: null
			}
		};
		var bM = aG.st;
		for (var R in bB) {
			if (bB[g](R) && !bM[g](R)) {
				bM[R] = (function(E) {
					return function() {
						var S = arguments;
						return this.forEach(function(bS) {
									bS[E].apply(bS, S)
								})
					}
				})(R)
			}
		}
	})();
(function() {
		if (!aG.vml) {
			return
		}
		var g = "hasOwnProperty", bS = String, bB = parseFloat, bw = Math, bP = bw.round, bV = bw.max, bQ = bw.min, bG = bw.abs, bJ = "fill", bx = /[, ]+/, bO = aG.eve, bK = " progid:DXImageTransform.Microsoft", bz = " ", bE = "", bR = {
			M : "m",
			L : "l",
			C : "c",
			Z : "x",
			m : "t",
			l : "r",
			c : "v",
			z : "x"
		}, by = /([clmz]),?([^clmz]*)/gi, bH = / progid:\S+Blur\([^\)]+\)/g, bU = /-?[^,\s-]+/g, d = "position:absolute;left:0;top:0;width:1px;height:1px", b = 21600, bN = {
			path : 1,
			rect : 1,
			image : 1
		}, bF = {
			circle : 1,
			ellipse : 1
		}, R = function(b4) {
			var b1 = /[ahqstv]/ig, bW = aG._pathToAbsolute;
			bS(b4).match(b1) && (bW = aG._path2curve);
			b1 = /[clmz]/g;
			if (bW == aG._pathToAbsolute && !bS(b4).match(b1)) {
				var b0 = bS(b4).replace(by, function(b8, ca, b6) {
							var b9 = [], b5 = ca.toLowerCase() == "m", b7 = bR[ca];
							b6.replace(bU, function(cb) {
										if (b5 && b9.length == 2) {
											b7 += b9
													+ bR[ca == "m" ? "l" : "L"];
											b9 = []
										}
										b9.push(bP(cb * b))
									});
							return b7 + b9
						});
				return b0
			}
			var b2 = bW(b4), S, E;
			b0 = [];
			for (var bY = 0, b3 = b2.length; bY < b3; bY++) {
				S = b2[bY];
				E = b2[bY][0].toLowerCase();
				E == "z" && (E = "x");
				for (var bX = 1, bZ = S.length; bX < bZ; bX++) {
					E += bP(S[bX] * b) + (bX != bZ - 1 ? "," : bE)
				}
				b0.push(E)
			}
			return b0.join(bz)
		}, bC = function(bX, bW, S) {
			var E = aG.matrix();
			E.rotate(-bX, 0.5, 0.5);
			return {
				dx : E.x(bW, S),
				dy : E.y(bW, S)
			}
		}, bD = function(b3, b2, b1, bY, bX, bZ) {
			var cb = b3._, b5 = b3.matrix, E = cb.fillpos, b4 = b3.node, b0 = b4.style, bW = 1, S = "", b7, b9 = b
					/ b2, b8 = b / b1;
			b0.visibility = "hidden";
			if (!b2 || !b1) {
				return
			}
			b4.coordsize = bG(b9) + bz + bG(b8);
			b0.rotation = bZ * (b2 * b1 < 0 ? -1 : 1);
			if (bZ) {
				var ca = bC(bZ, bY, bX);
				bY = ca.dx;
				bX = ca.dy
			}
			b2 < 0 && (S += "x");
			b1 < 0 && (S += " y") && (bW = -1);
			b0.flip = S;
			b4.coordorigin = (bY * -b9) + bz + (bX * -b8);
			if (E || cb.fillsize) {
				var b6 = b4.getElementsByTagName(bJ);
				b6 = b6 && b6[0];
				b4.removeChild(b6);
				if (E) {
					ca = bC(bZ, b5.x(E[0], E[1]), b5.y(E[0], E[1]));
					b6.position = ca.dx * bW + bz + ca.dy * bW
				}
				if (cb.fillsize) {
					b6.size = cb.fillsize[0] * bG(b2) + bz + cb.fillsize[1]
							* bG(b1)
				}
				b4.appendChild(b6)
			}
			b0.visibility = "visible"
		};
		aG.toString = function() {
			return "Your browser doesn\u2019t support SVG. Falling down to VML.\nYou are running Rapha\xebl "
					+ this.version
		};
		var bL = function(cb, cl) {
			cb.attrs = cb.attrs || {};
			var cg = cb.node, cq = cb.attrs, b7 = cg.style, b3, cj = bN[cb.type]
					&& (cl.x != cq.x || cl.y != cq.y || cl.width != cq.width
							|| cl.height != cq.height || cl.cx != cq.cx
							|| cl.cy != cq.cy || cl.rx != cq.rx
							|| cl.ry != cq.ry || cl.r != cq.r), ca = bF[cb.type]
					&& (cq.cx != cl.cx || cq.cy != cl.cy || cq.r != cl.r
							|| cq.rx != cl.rx || cq.ry != cl.ry), ct = cb;
			for (var b8 in cl) {
				if (cl[g](b8)) {
					cq[b8] = cl[b8]
				}
			}
			if (cj) {
				cq.path = aG._getPath[cb.type](cb);
				cb._.dirty = 1
			}
			cl.href && (cg.href = cl.href);
			cl.title && (cg.title = cl.title);
			cl.target && (cg.target = cl.target);
			cl.cursor && (b7.cursor = cl.cursor);
			"blur" in cl && cb.blur(cl.blur);
			if (cl.path && cb.type == "path" || cj) {
				cg.path = R(~bS(cq.path).toLowerCase().indexOf("r") ? aG
						._pathToAbsolute(cq.path) : cq.path);
				if (cb.type == "image") {
					cb._.fillpos = [cq.x, cq.y];
					cb._.fillsize = [cq.width, cq.height];
					bD(cb, 1, 1, 0, 0, 0)
				}
			}
			"transform" in cl && cb.transform(cl.transform);
			if (ca) {
				var bY = +cq.cx, bW = +cq.cy, b2 = +cq.rx || +cq.r || 0, b1 = +cq.ry
						|| +cq.r || 0;
				cg.path = aG.format("ar{0},{1},{2},{3},{4},{1},{4},{1}x",
						bP((bY - b2) * b), bP((bW - b1) * b),
						bP((bY + b2) * b), bP((bW + b1) * b), bP(bY * b));
				cb._.dirty = 1
			}
			if ("clip-rect" in cl) {
				var S = bS(cl["clip-rect"]).split(bx);
				if (S.length == 4) {
					S[2] = +S[2] + (+S[0]);
					S[3] = +S[3] + (+S[1]);
					var b9 = cg.clipRect || aG._g.doc.createElement("div"), cs = b9.style;
					cs.clip = aG.format("rect({1}px {2}px {3}px {0}px)", S);
					if (!cg.clipRect) {
						cs.position = "absolute";
						cs.top = 0;
						cs.left = 0;
						cs.width = cb.paper.width + "px";
						cs.height = cb.paper.height + "px";
						cg.parentNode.insertBefore(b9, cg);
						b9.appendChild(cg);
						cg.clipRect = b9
					}
				}
				if (!cl["clip-rect"]) {
					cg.clipRect && (cg.clipRect.style.clip = "auto")
				}
			}
			if (cb.textpath) {
				var cn = cb.textpath.style;
				cl.font && (cn.font = cl.font);
				cl["font-family"]
						&& (cn.fontFamily = '"'
								+ cl["font-family"].split(",")[0].replace(
										/^['"]+|['"]+$/g, bE) + '"');
				cl["font-size"] && (cn.fontSize = cl["font-size"]);
				cl["font-weight"] && (cn.fontWeight = cl["font-weight"]);
				cl["font-style"] && (cn.fontStyle = cl["font-style"])
			}
			if ("arrow-start" in cl) {
				addArrow(ct, cl["arrow-start"])
			}
			if ("arrow-end" in cl) {
				addArrow(ct, cl["arrow-end"], 1)
			}
			if (cl.opacity != null || cl["stroke-width"] != null
					|| cl.fill != null || cl.src != null || cl.stroke != null
					|| cl["stroke-width"] != null
					|| cl["stroke-opacity"] != null
					|| cl["fill-opacity"] != null
					|| cl["stroke-dasharray"] != null
					|| cl["stroke-miterlimit"] != null
					|| cl["stroke-linejoin"] != null
					|| cl["stroke-linecap"] != null) {
				var ch = cg.getElementsByTagName(bJ), co = false;
				ch = ch && ch[0];
				!ch && (co = ch = bT(bJ));
				if (cb.type == "image" && cl.src) {
					ch.src = cl.src
				}
				cl.fill && (ch.on = true);
				if (ch.on == null || cl.fill == "none" || cl.fill === null) {
					ch.on = false
				}
				if (ch.on && cl.fill) {
					var b0 = bS(cl.fill).match(aG._ISURL);
					if (b0) {
						ch.parentNode == cg && cg.removeChild(ch);
						ch.rotate = true;
						ch.src = b0[1];
						ch.type = "tile";
						var E = cb.getBBox(1);
						ch.position = E.x + bz + E.y;
						cb._.fillpos = [E.x, E.y];
						aG._preload(b0[1], function() {
									cb._.fillsize = [this.offsetWidth,
											this.offsetHeight]
								})
					} else {
						ch.color = aG.getRGB(cl.fill).hex;
						ch.src = bE;
						ch.type = "solid";
						if (aG.getRGB(cl.fill).error && (ct.type in {
							circle : 1,
							ellipse : 1
						} || bS(cl.fill).charAt() != "r")
								&& addGradientFill(ct, cl.fill, ch)) {
							cq.fill = "none";
							cq.gradient = cl.fill;
							ch.rotate = false
						}
					}
				}
				if ("fill-opacity" in cl || "opacity" in cl) {
					var bZ = ((+cq["fill-opacity"] + 1 || 2) - 1)
							* ((+cq.opacity + 1 || 2) - 1)
							* ((+aG.getRGB(cl.fill).o + 1 || 2) - 1);
					bZ = bQ(bV(bZ, 0), 1);
					ch.opacity = bZ;
					if (ch.src) {
						ch.color = "none"
					}
				}
				cg.appendChild(ch);
				var b4 = (cg.getElementsByTagName("stroke") && cg
						.getElementsByTagName("stroke")[0]), cr = false;
				!b4 && (cr = b4 = bT("stroke"));
				if ((cl.stroke && cl.stroke != "none") || cl["stroke-width"]
						|| cl["stroke-opacity"] != null
						|| cl["stroke-dasharray"] || cl["stroke-miterlimit"]
						|| cl["stroke-linejoin"] || cl["stroke-linecap"]) {
					b4.on = true
				}
				(cl.stroke == "none" || cl.stroke === null || b4.on == null
						|| cl.stroke == 0 || cl["stroke-width"] == 0)
						&& (b4.on = false);
				var cf = aG.getRGB(cl.stroke);
				b4.on && cl.stroke && (b4.color = cf.hex);
				bZ = ((+cq["stroke-opacity"] + 1 || 2) - 1)
						* ((+cq.opacity + 1 || 2) - 1) * ((+cf.o + 1 || 2) - 1);
				var cc = (bB(cl["stroke-width"]) || 1) * 0.75;
				bZ = bQ(bV(bZ, 0), 1);
				cl["stroke-width"] == null && (cc = cq["stroke-width"]);
				cl["stroke-width"] && (b4.weight = cc);
				cc && cc < 1 && (bZ *= cc) && (b4.weight = 1);
				b4.opacity = bZ;
				cl["stroke-linejoin"]
						&& (b4.joinstyle = cl["stroke-linejoin"] || "miter");
				b4.miterlimit = cl["stroke-miterlimit"] || 8;
				cl["stroke-linecap"]
						&& (b4.endcap = cl["stroke-linecap"] == "butt"
								? "flat"
								: cl["stroke-linecap"] == "square"
										? "square"
										: "round");
				if ("stroke-dasharray" in cl) {
					var ce = {
						"-" : "shortdash",
						"." : "shortdot",
						"-." : "shortdashdot",
						"-.." : "shortdashdotdot",
						". " : "dot",
						"- " : "dash",
						"--" : "longdash",
						"- ." : "dashdot",
						"--." : "longdashdot",
						"--.." : "longdashdotdot"
					};
					b4.dashstyle = ce[g](cl["stroke-dasharray"])
							? ce[cl["stroke-dasharray"]]
							: bE
				}
				cr && cg.appendChild(b4)
			}
			if (ct.type == "text") {
				ct.paper.canvas.style.display = bE;
				var ci = ct.paper.span, cd = 100, bX = cq.font
						&& cq.font.match(/\d+(?:\.\d*)?(?=px)/);
				b7 = ci.style;
				cq.font && (b7.font = cq.font);
				cq["font-family"] && (b7.fontFamily = cq["font-family"]);
				cq["font-weight"] && (b7.fontWeight = cq["font-weight"]);
				cq["font-style"] && (b7.fontStyle = cq["font-style"]);
				bX = bB(cq["font-size"] || bX && bX[0]) || 10;
				b7.fontSize = bX * cd + "px";
				ct.textpath.string
						&& (ci.innerHTML = bS(ct.textpath.string).replace(/</g,
								"&#60;").replace(/&/g, "&#38;").replace(/\n/g,
								"<br>"));
				var b6 = ci.getBoundingClientRect();
				ct.W = cq.w = (b6.right - b6.left) / cd;
				ct.H = cq.h = (b6.bottom - b6.top) / cd;
				ct.X = cq.x;
				ct.Y = cq.y + ct.H / 2;
				("x" in cl || "y" in cl)
						&& (ct.path.v = aG.format("m{0},{1}l{2},{1}", bP(cq.x
										* b), bP(cq.y * b), bP(cq.x * b) + 1));
				var b5 = ["x", "y", "text", "font", "font-family",
						"font-weight", "font-style", "font-size"];
				for (var ck = 0, cm = b5.length; ck < cm; ck++) {
					if (b5[ck] in cl) {
						ct._.dirty = 1;
						break
					}
				}
				switch (cq["text-anchor"]) {
					case "start" :
						ct.textpath.style["v-text-align"] = "left";
						ct.bbx = ct.W / 2;
						break;
					case "end" :
						ct.textpath.style["v-text-align"] = "right";
						ct.bbx = -ct.W / 2;
						break;
					default :
						ct.textpath.style["v-text-align"] = "center";
						ct.bbx = 0;
						break
				}
				ct.textpath.style["v-text-kern"] = true
			}
		}, bI = function(S, E) {
			this[0] = this.node = S;
			S.drag = true;
			this.id = aG._oid++;
			S.dragid = this.id;
			this.X = 0;
			this.Y = 0;
			this.attrs = {};
			this.paper = E;
			this.matrix = aG.matrix();
			this._ = {
				transform : [],
				sx : 1,
				sy : 1,
				dx : 0,
				dy : 0,
				deg : 0,
				dirty : 1,
				dirtyT : 1
			};
			!E.bottom && (E.bottom = this);
			this.prev = E.top;
			E.top && (E.top.next = this);
			E.top = this;
			this.next = null
		};
		var bA = aG.el;
		bI.prototype = bA;
		bA.constructor = bI;
		bA.transform = function(bY) {
			if (bY == null) {
				return this._.transform
			}
			var b0 = this.paper._viewBoxShift, bZ = b0 ? "s"
					+ [b0.scale, b0.scale] + "-1-1t" + [b0.dx, b0.dy] : bE, b3;
			if (b0) {
				b3 = bY = bS(bY).replace(/\.{3}|\u2026/g,
						this._.transform || bE)
			}
			aG._extractTransform(this, bZ + bY);
			var b4 = this.matrix.clone(), b6 = this.skew, bW = this.node, b2, bX = ~bS(this.attrs.fill)
					.indexOf("-"), E = !bS(this.attrs.fill).indexOf("url(");
			b4.translate(1, 1);
			if (E || bX || this.type == "image") {
				b6.matrix = "1 0 0 1";
				b6.offset = "0 0";
				b2 = b4.split();
				if ((bX && b2.noRotation) || !b2.isSimple) {
					bW.style.filter = b4.toFilter();
					var b1 = this.getBBox(), S = this.getBBox(1), b7 = b1.x
							- S.x, b5 = b1.y - S.y;
					bW.coordorigin = (b7 * -b) + bz + (b5 * -b);
					bD(this, 1, 1, b7, b5, 0)
				} else {
					bW.style.filter = bE;
					bD(this, b2.scalex, b2.scaley, b2.dx, b2.dy, b2.rotate)
				}
			} else {
				bW.style.filter = bE;
				b6.matrix = bS(b4);
				b6.offset = b4.offset()
			}
			b3 && (this._.transform = b3);
			return this
		};
		bA.rotate = function(S, E, bX) {
			if (this.removed) {
				return this
			}
			if (S == null) {
				return
			}
			S = bS(S).split(bx);
			if (S.length - 1) {
				E = bB(S[1]);
				bX = bB(S[2])
			}
			S = bB(S[0]);
			(bX == null) && (E = bX);
			if (E == null || bX == null) {
				var bW = this.getBBox(1);
				E = bW.x + bW.width / 2;
				bX = bW.y + bW.height / 2
			}
			this._.dirtyT = 1;
			this.transform(this._.transform.concat([["r", S, E, bX]]));
			return this
		};
		bA.translate = function(S, E) {
			if (this.removed) {
				return this
			}
			S = bS(S).split(bx);
			if (S.length - 1) {
				E = bB(S[1])
			}
			S = bB(S[0]) || 0;
			E = +E || 0;
			if (this._.bbox) {
				this._.bbox.x += S;
				this._.bbox.y += E
			}
			this.transform(this._.transform.concat([["t", S, E]]));
			return this
		};
		bA.scale = function(bY, bW, E, bX) {
			if (this.removed) {
				return this
			}
			bY = bS(bY).split(bx);
			if (bY.length - 1) {
				bW = bB(bY[1]);
				E = bB(bY[2]);
				bX = bB(bY[3]);
				isNaN(E) && (E = null);
				isNaN(bX) && (bX = null)
			}
			bY = bB(bY[0]);
			(bW == null) && (bW = bY);
			(bX == null) && (E = bX);
			if (E == null || bX == null) {
				var S = this.getBBox(1)
			}
			E = E == null ? S.x + S.width / 2 : E;
			bX = bX == null ? S.y + S.height / 2 : bX;
			this.transform(this._.transform.concat([["s", bY, bW, E, bX]]));
			this._.dirtyT = 1;
			return this
		};
		bA.hide = function() {
			!this.removed && (this.node.style.display = "none");
			return this
		};
		bA.show = function() {
			!this.removed && (this.node.style.display = bE);
			return this
		};
		bA._getBBox = function() {
			if (this.removed) {
				return {}
			}
			return {
				x : this.X + (this.bbx || 0) - this.W / 2,
				y : this.Y - this.H,
				width : this.W,
				height : this.H
			}
		};
		bA.remove = function() {
			if (this.removed || !this.node.parentNode) {
				return
			}
			this.paper.__set__ && this.paper.__set__.exclude(this);
			aG.eve.unbind("drag.*.*." + this.id);
			aG._tear(this, this.paper);
			this.node.parentNode.removeChild(this.node);
			this.shape && this.shape.parentNode.removeChild(this.shape);
			for (var E in this) {
				this[E] = typeof this[E] == "function"
						? aG._removedFactory(E)
						: null
			}
			this.removed = true
		};
		bA.attr = function(E, b3) {
			if (this.removed) {
				return this
			}
			if (E == null) {
				var b0 = {};
				for (var b2 in this.attrs) {
					if (this.attrs[g](b2)) {
						b0[b2] = this.attrs[b2]
					}
				}
				b0.gradient && b0.fill == "none" && (b0.fill = b0.gradient)
						&& delete b0.gradient;
				b0.transform = this._.transform;
				return b0
			}
			if (b3 == null && aG.is(E, "string")) {
				if (E == bJ && this.attrs.fill == "none" && this.attrs.gradient) {
					return this.attrs.gradient
				}
				var b1 = E.split(bx), bX = {};
				for (var bY = 0, b5 = b1.length; bY < b5; bY++) {
					E = b1[bY];
					if (E in this.attrs) {
						bX[E] = this.attrs[E]
					} else {
						if (aG.is(this.paper.customAttributes[E], "function")) {
							bX[E] = this.paper.customAttributes[E].def
						} else {
							bX[E] = aG._availableAttrs[E]
						}
					}
				}
				return b5 - 1 ? bX : bX[b1[0]]
			}
			if (this.attrs && b3 == null && aG.is(E, "array")) {
				bX = {};
				for (bY = 0, b5 = E.length; bY < b5; bY++) {
					bX[E[bY]] = this.attr(E[bY])
				}
				return bX
			}
			var S;
			if (b3 != null) {
				S = {};
				S[E] = b3
			}
			b3 == null && aG.is(E, "object") && (S = E);
			for (var b4 in S) {
				bO("drag.attr." + b4 + "." + this.id, this, S[b4])
			}
			if (S) {
				for (b4 in this.paper.customAttributes) {
					if (this.paper.customAttributes[g](b4)
							&& S[g](b4)
							&& aG.is(this.paper.customAttributes[b4],
									"function")) {
						var bZ = this.paper.customAttributes[b4].apply(this, []
										.concat(S[b4]));
						this.attrs[b4] = S[b4];
						for (var bW in bZ) {
							if (bZ[g](bW)) {
								S[bW] = bZ[bW]
							}
						}
					}
				}
				if (S.text && this.type == "text") {
					this.textpath.string = S.text
				}
				bL(this, S)
			}
			return this
		};
		bA.insertAfter = function(E) {
			if (this.removed) {
				return this
			}
			if (E.constructor == aG.st.constructor) {
				E = E[E.length - 1]
			}
			if (E.node.nextSibling) {
				E.node.parentNode.insertBefore(this.node, E.node.nextSibling)
			} else {
				E.node.parentNode.appendChild(this.node)
			}
			aG._insertafter(this, E, this.paper);
			return this
		};
		bA.insertBefore = function(E) {
			if (this.removed) {
				return this
			}
			if (E.constructor == aG.st.constructor) {
				E = E[0]
			}
			E.node.parentNode.insertBefore(this.node, E.node);
			aG._insertbefore(this, E, this.paper);
			return this
		};
		bA.blur = function(E) {
			var S = this.node.runtimeStyle, bW = S.filter;
			bW = bW.replace(bH, bE);
			if (+E !== 0) {
				this.attrs.blur = E;
				S.filter = bW + bz + bK + ".Blur(pixelradius=" + (+E || 1.5)
						+ ")";
				S.margin = aG.format("-{0}px 0 0 -{0}px", bP(+E || 1.5))
			} else {
				S.filter = bW;
				S.margin = 0;
				delete this.attrs.blur
			}
			return this
		};
		aG._engine.path = function(bX, S) {
			var bY = bT("shape");
			bY.style.cssText = d;
			bY.coordsize = b + bz + b;
			bY.coordorigin = S.coordorigin;
			var bZ = new bI(bY, S), E = {
				fill : "none",
				stroke : "#000"
			};
			bX && (E.path = bX);
			bZ.type = "path";
			bZ.path = [];
			bZ.Path = bE;
			bL(bZ, E);
			S.canvas.appendChild(bY);
			var bW = bT("skew");
			bW.on = true;
			bY.appendChild(bW);
			bZ.skew = bW;
			bZ.transform(bE);
			return bZ
		};
		aG._engine.rect = function(S, b0, bY, b1, bW, E) {
			var b2 = aG._rectPath(b0, bY, b1, bW, E), bX = S.path(b2), bZ = bX.attrs;
			bX.X = bZ.x = b0;
			bX.Y = bZ.y = bY;
			bX.W = bZ.width = b1;
			bX.H = bZ.height = bW;
			bZ.r = E;
			bZ.path = b2;
			bX.type = "rect";
			return bX
		};
		aG._engine.ellipse = function(S, E, b0, bZ, bY) {
			var bX = S.path(), bW = bX.attrs;
			bX.X = E - bZ;
			bX.Y = b0 - bY;
			bX.W = bZ * 2;
			bX.H = bY * 2;
			bX.type = "ellipse";
			bL(bX, {
						cx : E,
						cy : b0,
						rx : bZ,
						ry : bY
					});
			return bX
		};
		aG._engine.circle = function(S, E, bZ, bY) {
			var bX = S.path(), bW = bX.attrs;
			bX.X = E - bY;
			bX.Y = bZ - bY;
			bX.W = bX.H = bY * 2;
			bX.type = "circle";
			bL(bX, {
						cx : E,
						cy : bZ,
						r : bY
					});
			return bX
		};
		aG._engine.image = function(S, E, b1, bZ, b2, bX) {
			var b4 = aG._rectPath(b1, bZ, b2, bX), bY = S.path(b4).attr({
						stroke : "none"
					}), b0 = bY.attrs, bW = bY.node, b3 = bW
					.getElementsByTagName(bJ)[0];
			b0.src = E;
			bY.X = b0.x = b1;
			bY.Y = b0.y = bZ;
			bY.W = b0.width = b2;
			bY.H = b0.height = bX;
			b0.path = b4;
			bY.type = "image";
			b3.parentNode == bW && bW.removeChild(b3);
			b3.rotate = true;
			b3.src = E;
			b3.type = "tile";
			bY._.fillpos = [b1, bZ];
			bY._.fillsize = [b2, bX];
			bW.appendChild(b3);
			bD(bY, 1, 1, 0, 0, 0);
			return bY
		};
		aG._engine.text = function(E, b0, bZ, b1) {
			var bX = bT("shape"), b3 = bT("path"), bW = bT("textpath");
			b0 = b0 || 0;
			bZ = bZ || 0;
			b1 = b1 || "";
			b3.v = aG.format("m{0},{1}l{2},{1}", bP(b0 * b), bP(bZ * b), bP(b0
							* b)
							+ 1);
			b3.textpathok = true;
			bW.string = bS(b1);
			bW.on = true;
			bX.style.cssText = d;
			bX.coordsize = b + bz + b;
			bX.coordorigin = "0 0";
			var S = new bI(bX, E), bY = {
				fill : "#000",
				stroke : "none",
				font : aG._availableAttrs.font,
				text : b1
			};
			S.shape = bX;
			S.path = b3;
			S.textpath = bW;
			S.type = "text";
			S.attrs.text = bS(b1);
			S.attrs.x = b0;
			S.attrs.y = bZ;
			S.attrs.w = 1;
			S.attrs.h = 1;
			bL(S, bY);
			bX.appendChild(bW);
			bX.appendChild(b3);
			E.canvas.appendChild(bX);
			var b2 = bT("skew");
			b2.on = true;
			bX.appendChild(b2);
			S.skew = b2;
			S.transform(bE);
			return S
		};
		aG._engine.setSize = function(bW, E) {
			var S = this.canvas.style;
			this.width = bW;
			this.height = E;
			bW == +bW && (bW += "px");
			E == +E && (E += "px");
			S.width = bW;
			S.height = E;
			S.clip = "rect(0 " + bW + " " + E + " 0)";
			if (this._viewBox) {
				aG._engine.setViewBox.apply(this, this._viewBox)
			}
			return this
		};
		aG._engine.setViewBox = function(bZ, bY, b0, bW, bX) {
			aG
					.eve("drag.setViewBox", this, this._viewBox, [bZ, bY, b0,
									bW, bX]);
			var E = this.width, b2 = this.height, b3 = 1 / bV(b0 / E, bW / b2), b1, S;
			if (bX) {
				b1 = b2 / bW;
				S = E / b0;
				if (b0 * b1 < E) {
					bZ -= (E - b0 * b1) / 2 / b1
				}
				if (bW * S < b2) {
					bY -= (b2 - bW * S) / 2 / S
				}
			}
			this._viewBox = [bZ, bY, b0, bW, !!bX];
			this._viewBoxShift = {
				dx : -bZ,
				dy : -bY,
				scale : b3
			};
			this.forEach(function(b4) {
						b4.transform("...")
					});
			return this
		};
		var bT;
		aG._engine.initWin = function(bW) {
			var S = bW.document;
			S.createStyleSheet().addRule(".rvml", "behavior:url(#default#VML)");
			try {
				!S.namespaces.rvml
						&& S.namespaces.add("rvml",
								"urn:schemas-microsoft-com:vml");
				bT = function(bX) {
					return S.createElement("<rvml:" + bX + ' class="rvml">')
				}
			} catch (E) {
				bT = function(bX) {
					return S
							.createElement("<"
									+ bX
									+ ' xmlns="urn:schemas-microsoft.com:vml" class="rvml">')
				}
			}
		};
		aG._engine.initWin(aG._g.win);
		aG._engine.create = function() {
			var bW = aG._getContainer.apply(0, arguments), E = bW.container, b2 = bW.height, b3, S = bW.width, b1 = bW.x, b0 = bW.y;
			if (!E) {
				throw new Error("VML container not found.")
			}
			var bY = new aG._Paper, bZ = bY.canvas = aG._g.doc
					.createElement("div"), bX = bZ.style;
			b1 = b1 || 0;
			b0 = b0 || 0;
			S = S || 512;
			b2 = b2 || 342;
			bY.width = S;
			bY.height = b2;
			S == +S && (S += "px");
			b2 == +b2 && (b2 += "px");
			bY.coordsize = b * 1000 + bz + b * 1000;
			bY.coordorigin = "0 0";
			bY.span = aG._g.doc.createElement("span");
			bY.span.style.cssText = "position:absolute;left:-9999em;top:-9999em;padding:0;margin:0;line-height:1;";
			bZ.appendChild(bY.span);
			bX.cssText = aG
					.format(
							"top:0;left:0;width:{0};height:{1};display:inline-block;position:relative;clip:rect(0 {0} {1} 0);overflow:hidden",
							S, b2);
			if (E == 1) {
				aG._g.doc.body.appendChild(bZ);
				bX.left = b1 + "px";
				bX.top = b0 + "px";
				bX.position = "absolute"
			} else {
				if (E.firstChild) {
					E.insertBefore(bZ, E.firstChild)
				} else {
					E.appendChild(bZ)
				}
			}
			bY.renderfix = function() {
			};
			return bY
		};
		aG.prototype.clear = function() {
			aG.eve("drag.clear", this);
			this.canvas.innerHTML = bE;
			this.span = aG._g.doc.createElement("span");
			this.span.style.cssText = "position:absolute;left:-9999em;top:-9999em;padding:0;margin:0;line-height:1;display:inline;";
			this.canvas.appendChild(this.span);
			this.bottom = this.top = null
		};
		aG.prototype.remove = function() {
			aG.eve("drag.remove", this);
			this.canvas.parentNode.removeChild(this.canvas);
			for (var E in this) {
				this[E] = typeof this[E] == "function"
						? aG._removedFactory(E)
						: null
			}
			return true
		};
		var bM = aG.st;
		for (var bv in bA) {
			if (bA[g](bv) && !bM[g](bv)) {
				bM[bv] = (function(E) {
					return function() {
						var S = arguments;
						return this.forEach(function(bW) {
									bW[E].apply(bW, S)
								})
					}
				})(bv)
			}
		}
	})();
	q.was ? (ao.win.Drag = aG) : (Drag = aG);
	return aG
}));
Drag.fn.pieChart = function(c, b, q, e, w, j, k) {
	this.clear();
	var l = this, a = e.values, x = Math.PI / 180, p = this.set(), f = e.emptyRadius
			|| 50, n = e.text, o = e.textSuffix, h = e.textStyle || {}, d = e.textSuffixStyle
			|| {};
	h = {
		color : h.color || "#333",
		size : h.size || "26px",
		family : h.family || "Tahoma"
	};
	d = {
		color : d.color || "#333",
		size : d.size || "26px",
		family : d.family || "Tahoma"
	};
	function t(G, E, z, H, D, F, A) {
		var C = G + z * Math.cos(-H * x), B = G + z * Math.cos(-D * x), K = E
				+ z * Math.sin(-H * x), I = E + z * Math.sin(-D * x);
		return l.path(["M", G, E, "L", C, K, "A", z, z, 0, +(D - H > 180), 0,
				B, I, "z"]).attr(F).attr("cursor", "pointer")
	}
	var v = 0, y = 0, g = 0, s = function(B) {
		var E = a[B], H = 360 * E / y, F = v + (H / 2), A = k[B], r = 500, G = 30, D = "red", z = t(
				c, b, q, v, v + H, {
					fill : A,
					stroke : j
				}), C = l.text(c + (q + G + 55) * Math.cos(-F * x),
				b + (q + G + 25) * Math.sin(-F * x), w[B]).attr({
					fill : D,
					stroke : "none",
					opacity : 0,
					"font-size" : 20
				});
		z.mouseover(function() {
			z.stop().animate({
						transform : "s1.1 1.1 " + (c + 5) + " " + (b + 5)
					}, r, "elastic");
			var I = (B == 1) ? "firstpay" : ((B == 2)
					? "repayed"
					: "payedpoint");
			J.g(I).eq(0).addClass("hover")
		}).mouseout(function() {
			z.stop().animate({
						transform : ""
					}, r, "elastic");
			var I = (B == 1) ? "firstpay" : ((B == 2)
					? "repayed"
					: "payedpoint");
			J.g(I).eq(0).removeClass("hover")
		});
		v += H;
		p.push(z);
		p.push(C);
		g += 0.1
	};
	for (var u = 0, m = a.length; u < m; u++) {
		y += a[u]
	}
	for (u = 0; u < m; u++) {
		s(u)
	}
	l.circle(c, b, f).attr({
				fill : "#fff",
				stroke : "none"
			});
	p.midText = l.text(c, b - 5, n).attr({
				id : "midtext",
				fill : h.color,
				"font-weight" : "bold",
				"font-size" : h.size,
				"font-family" : h.family
			});
	l.text(c, b + 25, o).attr({
				fill : d.color,
				"font-size" : d.size,
				"font-family" : d.family
			});
	return p
};
J.ready(function() {
	var j = Drag("piechart", "100%", "100%");
	var d = ((commInform.Allprice) - commInform.firsetPrice)
			/ (commInform.Allprice);
	d = d + "";
	if (0 === parseFloat(d)) {
		J.g("refer_month").get().style.display = "none";
		return
	}
	if (d.length >= 3) {
		d = d.substring(0, 3).match(/\d+$/)[0];
		J.g("repayed").s("span").eq(1).html(d + "成");
		J.g("firstpay").s("span").eq(0).html(parseFloat(J.g("firstpay")
				.s("span").eq(0).html()).toFixed(0));
		var h = b((commInform.Allprice * (parseInt(d) * 0.1) * 10000), g(
						parseInt(J.g("paies").val()), parseInt(J.g("yeares")
								.val())), parseInt(J.g("yeares").val()) * 12);
		J.g("yearpays").html(h);
		J.g("reference_monthpay").html(h + "元");
		J.s("#pointes option").each(function(k, a) {
					if (J.g(a).attr("value") == d) {
						J.g("pointes").get().options[k].selected = true
					}
				});
		J.g("payedpoint").s("span").eq(0).html(parseFloat(e(h) / 10000)
				.toFixed(0));
		J.g("payedpoint").s("span").eq(1).html((g(1, d) * 100).toFixed(2))
	}
	f();
	var c = (commInform.Allprice * 10000 * (d * 0.1)) / 10000;
	J.g("repayed").s("span").eq(0).html(parseFloat(c).toFixed(0));
	J.g("firstpay").s("span").eq(1).html((10 - J.g("pointes").val()) + "成");
	J.g("pointes").on("change", function() {
		var l = parseInt(this.value);
		var m = b(
				((commInform.Allprice * (parseInt(J.g("pointes").val())) * 0.1) * 10000),
				g(parseInt(J.g("paies").val()), parseInt(J.g("yeares").val())),
				parseFloat(J.g("yeares").val()) * 12);
		J.g("yearpays").html(m);
		var k = (commInform.Allprice * 10000 * (l * 0.1)) / 10000;
		J.g("repayed").s("span").eq(0).html(parseFloat(k).toFixed(0));
		J.g("repayed").s("span").eq(1).html(l + "成");
		J
				.g("firstpay")
				.s("span")
				.eq(0)
				.html(parseFloat((((commInform.Allprice * 10000) * ((10 - l) * 0.1)) / 10000))
						.toFixed(0));
		J.g("firstpay").s("span").eq(1).html((10 - l) + "成");
		J.g("payedpoint").s("span").eq(0).html(parseFloat(e(m) / 10000)
				.toFixed(0));
		J.g("payedpoint").s("span").eq(1).html((g(parseInt(J.g("paies").val()),
				parseInt(J.g("yeares").val())) * 100).toFixed(2));
		f();
		J.site.trackEvent("piechartforb")
	});
	J.g("yeares").on("change", function() {
		J.g("yearcounts").html(this.value);
		var a = b(
				((commInform.Allprice * (parseInt(J.g("pointes").val())) * 0.1) * 10000),
				g(parseInt(J.g("paies").val()), parseInt(J.g("yeares").val())),
				parseFloat(J.g("yeares").val()) * 12);
		J.g("yearpays").html(a);
		J.g("payedpoint").s("span").eq(0).html(parseFloat(e(a) / 10000)
				.toFixed(0));
		J.g("payedpoint").s("span").eq(1).html((g(parseInt(J.g("paies").val()),
				parseInt(J.g("yeares").val())) * 100).toFixed(2));
		f();
		J.site.trackEvent("piechartforc")
	});
	J.g("paies").on("change", function() {
		var a = b(
				((commInform.Allprice * (parseInt(J.g("pointes").val())) * 0.1) * 10000),
				g(parseInt(J.g("paies").val()), parseInt(J.g("yeares").val())),
				parseFloat(J.g("yeares").val()) * 12);
		J.g("yearpays").html(a);
		J.g(this).next().get().setAttribute("index", this.value);
		J.g("payedpoint").s("span").eq(0).html(parseFloat(e(a) / 10000)
				.toFixed(0));
		J.g("payedpoint").s("span").eq(1).html((g(parseInt(J.g("paies").val()),
				parseInt(J.g("yeares").val())) * 100).toFixed(2));
		f();
		J.site.trackEvent("piechartfora")
	});
	function b(l, k, n) {
		var m = (l * (k / 12) * (Math.pow((1 + k / 12), n)))
				/ (Math.pow((1 + k / 12), (n)) - 1);
		return parseInt(m)
	}
	function g(n, l) {
		var o = 0;
		if (n == "1") {
			var k = l * 12;
			if (k <= 6) {
				o = cal_brate[0]
			} else {
				if (k > 6 && k <= 12) {
					o = cal_brate[1]
				} else {
					if (k > 12 && k <= 36) {
						o = cal_brate[2]
					} else {
						if (k > 36 && k <= 60) {
							o = cal_brate[3]
						} else {
							o = cal_brate[4]
						}
					}
				}
			}
		} else {
			if (n == "2") {
				l <= 5 ? o = cal_grate[0] : o = cal_grate[1]
			}
		}
		return o
	}
	function e(a) {
		var k = (a * parseInt(J.g("yeares").val()) * 12)
				- ((commInform.Allprice * (parseInt(J.g("pointes").val())) * 0.1) * 10000);
		return (k > 0) ? k : 0
	}
(function() {
		var m = J.s(".icon-help.icon-paies"), l = J.s(".p_arowCon.commerce")
				.eq(0), a = J.s(".p_arowCon.fund").eq(0), n = J
				.s(".p_arowCon.ratio").eq(0), k = J.g("paies").eq(0);
		J.s(".icon-help.icon-paies").eq(0).on("mouseover", function(o) {
					if (parseInt(k.val()) === 1) {
						l.get().style.display = "block"
					}
					if (parseInt(k.val()) === 2) {
						a.get().style.display = "block"
					}
				});
		J.s(".icon-help.icon-paies").eq(0).on("mouseout", function(o) {
					if (parseInt(k.val()) === 1) {
						l.get().style.display = "none"
					}
					if (parseInt(k.val()) === 2) {
						a.get().style.display = "none"
					}
				});
		J.s(".icon-help.icon-ratio").eq(0).on("mouseover", function(o) {
					n.get().style.display = "block"
				});
		J.s(".icon-help.icon-ratio").eq(0).on("mouseout", function(o) {
					n.get().style.display = "none"
				})
	})();
	function f(m, l, p) {
		var k = [parseInt(J.g("payedpoint").s("span").eq(0).html()),
				parseInt(J.g("firstpay").s("span").eq(0).html()),
				parseInt(J.g("repayed").s("span").eq(0).html())];
		var o = parseInt(J.g("yearpays").eq(0).html()) || 0;
		var n = {
			emptyRadius : 65,
			text : "\uffe5" + ((o < 999999) ? o : "999999+"),
			textStyle : {
				color : "#333",
				size : "26px",
				family : "Tahoma"
			},
			textSuffix : "/每月",
			textSuffixStyle : {
				color : "#999",
				size : "16px",
				family : "microsoft yahei"
			},
			values : k
		};
		j.pieChart(145, 127, 106, n, [".", ".", "."], "#fff", ["#62BC4B",
						"#FFA644", "#2F69BF"])
	}
});
function CreateChart(e, d, a, c, b) {
	this.tabs = [];
	this.obj = e;
	this.datas = d;
	this.items = a;
	this.position = [];
	this.priceTab = [];
	this.monthTab = [];
	this.colletion = [];
	this.options = b || {};
	this.paper = Drag(e, "100%", "100%");
	this.extraData = c;
	this.init()
}
CreateChart.prototype = {
	init : function() {
		var a = J.g(this.obj).width();
		this.createAxis(7, 30, a - 65, 0, 40)
	},
	createAxis : function(c, b, j, h, d) {
		this.axisWidth = j;
		var e = Drag.svg ? 0.5 : 0.1, g, a;
		for (var f = 0; f < c; f++) {
			a = f ? (((f * b) + d) + e) : ((f + 1) * d + e);
			this.paper.path([["M", "0", a], ["L", j, a]]).attr({
						stroke : (f == c - 1) ? "#CCC" : "#EFEFEF"
					});
			this.position.push(a)
		}
		this.paper.path([["M", (j + e), a], ["L", (j + e), (b - h)]]).attr({
					stroke : "#CCC"
				});
		if (this.datas.status != "ok") {
			this.noData(false);
			return
		}
		this.createTimeTabs();
		this.addtitle();
		this.noData(true);
		this.createPriceTab(6);
		this.setOneYearData()
	},
	animate : function(f, h, g, e, b) {
		var d = 0, c = this, a = [];
		return (function() {
			var j = (arguments.callee), k = h.slice(0, ++d);
			f.animate({
						path : k
					}, 80, "linear", function() {
						if (h[d - 1]) {
							j(f, h, g);
							var l = c.paper.circle(h[d - 2][1], h[d - 2][2], 0)
									.attr({
												stroke : e,
												fill : b,
												"stroke-width" : 2
											});
							a.push(l)
						} else {
							if (d == (h.length + 1)) {
								g && g(a);
								j = null
							}
						}
					})
		})()
	},
	createTimeTabs : function() {
		for (var a = 0; a < 4; a++) {
			var b = document.createElement("a");
			b.href = "javascript:;";
			if (!a) {
				this.chartItem = b;
				this.chartItem.className = "chartTimeTab";
				this.obj.appendChild(this.chartItem)
			} else {
				this.tabs.push(b);
				b.className = "chartItems";
				b.innerHTML = this.items[a - 1];
				this.chartItem.appendChild(b)
			}
		}
		this.addClass(this.tabs[1], "hover");
		this.bindEvent()
	},
	create : function(b, d, c, a) {
		var e = document.createElement(b);
		if (c) {
			e.className = c
		}
		if (a) {
			e.innerHTML = a
		}
		d.appendChild(e);
		return e
	},
	addtitle : function() {
		this.logo = this.create("img", this.obj, "thishouse");
		this.logo.src = this.options.icon
	},
	dragPath : function(B, u, p, h, n, v, d, e, b, g, l) {
		var D = [], j = this, c = [];
		p = parseInt(p);
		var o = Math.max(g, this.extraData.avgPrice), y = Math.min(h,
				this.extraData.avgPrice), r = (o - y) / 5;
		var m = this.returnRange(r) + "", a = (m.substring(1)), f = new RegExp("\\d{"
				+ a.length + "}$"), s = y.toString() ? y.toString().replace(f,
				a) : 0, x = [], A = parseInt(m / 30), z = [], f = /(?:\d{2})$/;
		for (var w = 0; w < 6; w++) {
			x.push(parseInt(s) + (w * m));
			this.priceTab[w].attr({
						text : (parseInt(s) + (w * m)) + "\u5143",
						y : (this.position[5 - w]),
						x : (this.axisWidth + 29)
					})
		}
		for (var w = 0; w < this.monthTab.length; w++) {
			if (l) {
				var C = n[w].substring(0, 4) + "." + parseInt(f.exec(n[w])[0]);
				this.monthTab[w].attr({
							"font-family" : "Arial"
						})
			} else {
				var C = parseInt(f.exec(n[w])[0]) + "\u6708"
			}
			if (this.monthTab[w].attr("text") == C) {
				continue
			} else {
				this.monthTab[w].attr("text", C)
			}
		}
		for (var w = 0; w < B.length; w++) {
			var k = (190.5 - ((B[w] - s) / A));
			(k < 0) && (k = 5);
			z.push(["L", (this.monthTab[w].attr("x")), (k)])
		}
		z[0][0] = "M";
		var q = this.paper.path().attr({
					stroke : v,
					"stroke-width" : 2
				});
		this.colletion.push(q);
		var t = this.extraData.avgPrice;
		this.animate(q, z, function(E) {
					j.callBack(E, e, b);
					var F = ((190.5 - ((t - s) / A)));
					if (F < 22.5) {
						F = 22.5
					}
					j.setStyle(j.logo, {
								top : F,
								left : (z[z.length - 1][1] - 8)
							})
				}, v, d)
	},
	clearOverLay : function() {
		var c = this.colletion.concat(this.monthTab);
		for (var a = c.length - 1, b = 0; a >= b; a--) {
			c[a].remove()
		}
		this.setStyle(this.logo, {
					top : -999,
					left : -999
				});
		this.colletion = [];
		this.monthTab = []
	},
	callBack : function(b, a, c) {
		var f = this;
		var g = document.getElementById("chartOverLay");
		for (var d = 0, e = b.length; d < e; d++) {
			(function(h) {
				f.colletion.push(b[h]);
				b[h].animate({
							r : a
						}, 500, "linear").data("index", h).attr({
							cursor : "pointer"
						});
				b[h].mouseover(function(l) {
							if (this.data("index") < (c.title.length - 3)) {
								f.addClass(g, "chartRight");
								f.setStyle(g, {
											left : parseInt(b[h].attr("cx")
													- 34),
											top : parseInt(b[h].attr("cy")
													- 102),
											display : "block"
										})
							} else {
								f.removeClass(g, "chartRight");
								f.setStyle(g, {
											left : parseInt(b[h].attr("cx")
													- 144),
											top : parseInt(b[h].attr("cy")
													- 102),
											display : "block"
										})
							}
							var j = /\d{2}$/;
							var k = g.getElementsByTagName("span");
							g.getElementsByTagName("h3")[0].innerHTML = c.title[h]
									.substring(0, 4)
									+ "年"
									+ parseInt(c.title[h].substring(4))
									+ "月";
							k[0].innerHTML = c.community[h];
							k[1].innerHTML = c.area[h];
							this.animate({
										r : 7
									}, "linear")
						});
				b[h].mouseout(function() {
							if (b[h].attr("fill").toLowerCase() == "#2f69bf") {
								this.animate({
											r : 4
										}, "linear")
							} else {
								b[h].attr("fill", "#FF5C00");
								this.animate({
											r : 4
										}, "linear")
							}
							f.setStyle(g, {
										display : "none"
									})
						})
			})(d)
		}
	},
	getMaxData : function(k, f, r) {
		var p = r ? k.community : k.community.slice(k.community.length - f), o = r
				? k.area
				: k.area.slice(k.area.length - f), n = p.concat(o), m = [], l = [];
		for (var h = 0, q = n.length; h < q; h++) {
			for (var g in n[h]) {
				l.push(g);
				m.push(n[h][g])
			}
		}
		return {
			community : m.slice(0, f),
			area : m.slice(f),
			title : l.slice(0, f),
			min : m.sort(this.sort)[0],
			max : m.sort(this.sort)[m.length - 1]
		}
	},
	setSixMonthData : function() {
		var c = 6;
		this.clearOverLay();
		this.createTimeAxis(c);
		var b = this.getMaxData(this.datas, c);
		if (b.max <= 0) {
			this.noData(false)
		} else {
			this.noData(true);
			var a = (b.max - b.min) / 5;
			this.dragPath(b.area, c, a, b.min, b.title, "#2F69BF", "#2F69BF",
					4, b, b.max);
			this.dragPath(b.community, c, a, b.min, b.title, "#FF5C00",
					"#FF5C00", 4, b, b.max)
		}
	},
	setOneYearData : function() {
		var c = 12;
		this.clearOverLay();
		this.createTimeAxis(c);
		var b = this.getMaxData(this.datas, c);
		if (b.max <= 0) {
			this.noData(false)
		} else {
			this.noData(true);
			var a = (b.max - b.min) / 5;
			this.dragPath(b.area, c, a, b.min, b.title, "#2F69BF", "#2F69BF",
					4, b, b.max);
			this.dragPath(b.community, c, a, b.min, b.title, "#FF5C00",
					"#FF5C00", 4, b, b.max)
		}
	},
	setThreeYearData : function() {
		var e = 12;
		var d = this;
		this.clearOverLay();
		this.createTimeAxis(e);
		var a = {
			community : d.returnThreeData(d.datas.community),
			area : d.returnThreeData(d.datas.area)
		};
		var c = this.getMaxData(a, e);
		if (c.max <= 0) {
			this.noData(false)
		} else {
			this.noData(true);
			var b = (c.max - c.min) / 5;
			objs = this.dragPath(c.area, e, b, c.min, c.title, "#2F69BF",
					"#2F69BF", 4, c, c.max, true);
			this.dragPath(c.community, e, b, c.min, c.title, "#FF5C00",
					"#FF5C00", 4, c, c.max, true)
		}
	},
	createPriceTab : function(b) {
		this.font = {
			font : "12px Helvetica, Arial",
			fill : "#666"
		};
		for (var a = 0, c = b; a < c; a++) {
			var d = this.paper.text(0, 0, "price").attr(this.font);
			this.priceTab.push(d)
		}
	},
	hasClass : function(c, b) {
		var d = c.className.split(/\s+/);
		for (var a = 0; a < d.length; a++) {
			if (d[a] == b) {
				return true
			}
		}
		return false
	},
	addClass : function(b, a) {
		if (!this.hasClass(b, a)) {
			b.className += " " + a
		}
		return b
	},
	removeClass : function(d, b) {
		var c = d.className.split(/\s+/);
		for (var a = 0; a < c.length; a++) {
			if (c[a] == b) {
				delete c[a]
			}
		}
		d.className = c.join(" ");
		return d
	},
	noData : function(c) {
		var b = ["thishousenull", "commhousenull", "areahousenull",
				"chartnoData"];
		var a = ["thishousedesc", "commhousedesc", "areahousedesc"];
		if (c) {
			for (var d = 0; d < b.length; d++) {
				J.g(b[d]).setStyle({
							display : "none"
						})
			}
			for (var d = 0; d < a.length; d++) {
				J.g(a[d]).setStyle({
							display : "block"
						})
			}
		} else {
			for (var d = 0; d < b.length; d++) {
				J.g(b[d]).setStyle({
							display : "block"
						})
			}
			for (var d = 0; d < a.length; d++) {
				J.g(a[d]).setStyle({
							display : "none"
						})
			}
		}
	},
	setStyle : function(c, b) {
		if (!c) {
			return
		}
		for (var a in b) {
			if (a == "left" || a == "top") {
				c.style[a] = b[a] + "px"
			} else {
				c.style[a] = b[a]
			}
		}
	},
	sort : function(d, c) {
		return d - c
	},
	removeHover : function(a) {
		for (var b = 0, c = this.tabs.length; b < c; b++) {
			this.removeClass(this.tabs[b], "hover")
		}
		this.addClass(this.tabs[a], "hover")
	},
	createTimeAxis : function(c, e) {
		var a = this.axisWidth / c;
		for (var b = 0; b < c; b++) {
			var d = this.paper.text(0, 0, "").attr(this.font);
			if (!d.attr) {
				return
			}
			if (!b) {
				d.attr({
							x : 19,
							y : 235,
							text : ""
						});
				this.monthTab.push(d)
			} else {
				d.attr({
							x : (b * a) + 19,
							y : 235,
							text : ""
						});
				this.monthTab.push(d)
			}
		}
	},
	toChange : function(b) {
		var a = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"];
		return a[(b + 1)] || ""
	},
	returnThreeData : function(f) {
		var e = /(03|06|09|12)$/, a = [];
		for (var c = f.length, d = 0; c >= d; c--) {
			for (var b in f[c]) {
				if ((e.test(b)) && (a.length < 12)) {
					a.push(f[c])
				}
			}
		}
		return a.reverse()
	},
	bindEvent : function() {
		var c = this;
		for (var a = 0, b = this.tabs.length; a < b; a++) {
			(function(d) {
				c.tabs[d].onclick = function() {
					c.removeHover(d);
					switch (d) {
						case 0 :
							c.setSixMonthData();
							break;
						case 1 :
							c.setOneYearData();
							break;
						case 2 :
							c.setThreeYearData();
							break
					}
				}
			})(a)
		}
	},
	returnRange : function(a) {
		if (a >= 0 && a <= 90) {
			return 100
		} else {
			if (a >= 90 && a <= 190) {
				return 200
			} else {
				if (a >= 190 && a <= 290) {
					return 300
				} else {
					if (a >= 290 && a <= 390) {
						return 400
					} else {
						if (a >= 390 && a <= 490) {
							return 500
						} else {
							if (a >= 490 && a <= 590) {
								return 600
							} else {
								if (a >= 590 && a <= 790) {
									return 800
								} else {
									if (a >= 790 && a <= 990) {
										return 1000
									} else {
										if (a >= 990 && a <= 1900) {
											return 2000
										} else {
											if (a >= 1900 && a <= 2900) {
												return 3000
											} else {
												if (a >= 2900 && a <= 3900) {
													return 4000
												} else {
													if (a >= 3900 && a <= 4900) {
														return 5000
													} else {
														if (a >= 4900
																&& a <= 7900) {
															return 8000
														} else {
															if (a >= 7900
																	&& a <= 9800) {
																return 10000
															} else {
																if (a >= 9800
																		&& a <= 19800) {
																	return 20000
																} else {
																	if (a >= 19800
																			&& a <= 29800) {
																		return 30000
																	} else {
																		if (a >= 29800
																				&& a <= 49800) {
																			return 50000
																		} else {
																			if (a >= 49800
																					&& a <= 99800) {
																				return 100000
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
};
J.ready(function() {
	var c = {
		BALANCE : "--持平",
		UP : "↑",
		DOWN : "↓"
	};
	function b(g) {
		var f = document.createElement("script");
		f.async = true;
		f.src = g;
		var j = document.getElementsByTagName("head")[0];
		if (j) {
			j.insertBefore(f, j.lastChild)
		}
	}
	function e(h) {
		var f = {};
		for (var g in h) {
			f.date = g;
			f.data = h[g]
		}
		return f
	}
	function d(m) {
		var r = J.s(".block-self .avg"), n = J.s(".block-self .price-stat"), f = J
				.s(".block-self .percent"), p = J.s(".block-self .status"), g = J
				.s(".block-community .avg"), s = J.s(".block-community .month"), k = J
				.s(".block-community .price-stat"), l = J
				.s(".block-community .status"), u = J
				.s(".block-community .percent"), q = J.s(".block-area .avg"), j = J
				.s(".block-area .month"), o = J.s(".block-area .price-stat"), h = J
				.s(".block-area .status"), t = J.s(".block-area .percent");
		r.eq(0).html(m.houseAvgPrice);
		n.eq(0).removeClass("down");
		n.eq(0).removeClass("up");
		n.eq(0).addClass(m.houseStatistics);
		f.eq(0).html(m.housePercent);
		p.eq(0).html(m.houseStatus);
		g.eq(0).html(m.communityAvgPrice);
		s.eq(0).html(m.communityMonth);
		k.eq(0).removeClass("down");
		k.eq(0).removeClass("up");
		k.eq(0).addClass(m.communityStatistics);
		l.eq(0).html(m.communityStatus);
		u.eq(0).html(m.communityPercent);
		q.eq(0).html(m.areaAvgPrice);
		j.eq(0).html(m.areaMonth);
		o.eq(0).removeClass("down");
		o.eq(0).removeClass("up");
		o.eq(0).addClass(m.areaStatistics);
		h.eq(0).html(m.areaStatus);
		t.eq(0).html(m.areaPercent)
	}
	function a(w) {
		if (undefined === w) {
			w = {
				status : "failed",
				community : [],
				area : []
			}
		}
		var m = priceTrend.data;
		var o = ["6\u4e2a\u6708", "1\u5e74", "3\u5e74"];
		var p = new CreateChart(J.g("linechart").get(), w, o, m, priceTrend);
		var g = {}, s = {};
		g.obj = [], s.obj = [], g.pices = [], s.pices = [];
		g.obj.push(w.area[w.area.length - 1], w.area[w.area.length - 2]);
		s.obj.push(w.community[w.community.length - 1],
				w.community[w.community.length - 2]);
		var r = w.community && w.community.length;
		var j = e(w.community.slice(r - 1)[0]);
		var v = parseFloat(w.comm_midchange) * 100;
		var l = parseInt(j.date.slice(-2)) - 1;
		var q = priceTrend.data.avgPrice;
		var f = (j.data == 0) ? "" : ((q - j.data) / j.data) * 100;
		var k = w.area && w.area.length;
		var t = e(w.area.slice(k - 1)[0]);
		var u = parseFloat(w.area_midchange) * 100;
		var h = parseInt(t.date.slice(-2)) - 1;
		var n = {
			houseAvgPrice : q,
			housePercent : (f == 0) ? "" : Math.round(Math.abs(f)) + "%",
			houseStatistics : (f == 0) ? "" : ((f < 0) ? "down" : "up"),
			houseStatus : (f == 0) ? c.BALANCE : ((f < 0) ? c.DOWN : c.UP),
			communityAvgPrice : j.data,
			communityMonth : l ? l : 12,
			communityPercent : (v == 0) ? "" : Math.round(Math.abs(v)) + "%",
			communityStatistics : (v == 0) ? "" : ((v < 0) ? "down" : "up"),
			communityStatus : (v == 0) ? c.BALANCE : ((v < 0) ? c.DOWN : c.UP),
			areaAvgPrice : t.data,
			areaMonth : h ? h : 12,
			areaPercent : (u == 0) ? "" : Math.round(Math.abs(u)) + "%",
			areaStatistics : (u == 0) ? "" : ((u < 0) ? "down" : "up"),
			areaStatus : (u == 0) ? c.BALANCE : ((u < 0) ? c.DOWN : c.UP)
		};
		d(n)
	}
	J.get({
				url : priceTrend.url,
				type : "json",
				data : {
					commid : priceTrend.data.id
				},
				timeout : 20000,
				onSuccess : a,
				onFailure : function(f) {
				},
				onTimeout : function(f) {
				}
			})
});
J.ready(function() {
	var a = J.g("commmap");
	if (a.length != 0) {
		var c = {
			commid : link_info.comm_id,
			rand : Math.random()
		}, b = anjuke_city_url || "";
		J.get({
					url : b + "/ajax/communityext/",
					data : c,
					type : "jsonp",
					callback : "communityext_callback"
				});
		communityext_callback = function(l) {
			if (l && l.comm_propnum) {
				var k = l.comm_propnum, g = parseInt(k.rentNum) || 0, d = parseInt(k.saleNum)
						|| 0;
				var j = J.g("rentRate"), e = J.g("sumProp"), h = g + d, f = g
						/ h || 0;
				j.length && j.html(parseInt(f * 100) + "%");
				if (pro_type == 1) {
					e.html("(共" + d + "套房源)")
				} else {
					if (pro_type == 2 || pro_type == 3) {
						e.html("(共" + g + "套租房)")
					} else {
						if (pro_type == 13) {
							e.html("(共" + g + "套房源)")
						}
					}
				}
				J.g("p-phrase-rent-num").html(g);
				J.g("p-phrase-sale-num").html(d)
			}
		}
	}
});
J.ready(function() {
	var c = J.g("life_box");
	var e = J.g("map_tab_title");
	var a = {
		traffic : {
			name : "交通",
			score : 4,
			distance : "步行15分钟内的公交设施",
			tip : "本评分根据交通种类、数量、距离等因素计算得出",
			attr : {
				metro_station : "个地铁站",
				bus_station : "个公交站"
			}
		},
		school : {
			name : "教育",
			score : 4,
			distance : "1公里范围内的学校",
			tip : "本评分根据附近学校的种类、数量、距离等因素计算得出",
			attr : {
				middlel : "所中学",
				junior : "所小学",
				nursery : "所幼儿园"
			}
		},
		hospital : {
			name : "医疗",
			score : 4,
			distance : "5公里范围内的医疗机构",
			tip : "本评分根据医院的级别、种类、性质、数量、距离等因素计算得出",
			attr : {
				level_three : "所三级医院",
				level_tow : "所二级医院",
				level_one : "所一级医院",
				level_comm : "所普通医院"
			}
		},
		commerce : {
			name : "商业",
			score : 4,
			distance : "步行15分钟内的商业设施",
			tip : "本评分根据银行/餐饮/超市的数量、距离等因素计算得出",
			attr : {
				restaurant : "个餐馆",
				bank : "个银行和ATM机",
				supermarket : "个超市"
			}
		}
	};
	function b(h) {
		if (h && h.stopPropagation) {
			h.stopPropagation()
		} else {
			window.event.cancelBubble = true
		}
	}
	function d() {
		var j = comm_info.comm_id;
		var l = city_id, h = anjuke_city_url || "";
		var k = h + "/v3/ajax/nearby/?commid=" + j + "&cityid=" + l;
		J.get({
					url : k,
					cache : false,
					type : "jsonp",
					callback : "life_url_callback"
				});
		life_url_callback = function(m) {
			if (m && m.status == "true") {
				g(m.result);
				J.g("star_greet").length
						&& (J.g("star_greet").get().style.width = (m.result.greet / 10)
								* 100 + "%");
				if (m.result.comfort) {
					J.g("star_comfort").length
							&& (J.g("star_comfort").get().style.width = (m.result.comfort / 10)
									* 100 + "%")
				} else {
					J.g("comfort_info").hide()
				}
				if (m.result.traffic || m.result.hospital || m.result.school
						|| m.result.commerce) {
					c && c.s(".lifeBox").each(function(p, u) {
								var s = u.s(".nlist").eq(0);
								var o = u.s(".gray").eq(0);
								var t = u.s(".lifeMap").eq(0);
								var w = u.s(".p_star_k").eq(0);
								var r = u.s(".p_arowCon").eq(0);
								w.on("mouseenter", function() {
											r.show()
										});
								w.on("mouseleave", function() {
											r.hide()
										});
								var q = u.attr("data-attr");
								f(q, m.result[q]["attr"], s, t);
								var n = u.s(".lifeMap").eq(0).s("span");
								if (s.html() == "") {
									s.html("较少")
								}
								if (t.html() == "") {
									t
											.html('<span class="ico_none lifex"></span>')
								}
								if (n.length > 3) {
									n.eq(3).hide()
								}
							});
					J.g("life_near_box").show();
					J.g("mappop_content").length && areaMap.AreaBlock()
				} else {
					J.g("life_near_box").hide()
				}
			} else {
				J.g("life_near_box").hide()
			}
		}
	}
	function g(l) {
		var k = "";
		var h = "";
		for (var j in l) {
			if (j != "comfort" && j != "greet") {
				k += '<div data-attr="' + j + '" class="box item lifeBox ' + j
						+ ' cf">';
				k += '<div class="nleft fl lifeLife">';
				k += '<div class="p_title cf">';
				k += "<h6>" + a[j].name + "</h6>";
				k += '<div class="p_star">';
				k += '<a class="p_star_k"><em class="p_star_s" style="width:'
						+ ((l[j].score / 10) * 100 || "0") + '%;"></em></a>';
				k += '<div class="p_arowCon" style="display:none"><p> '
						+ a[j].tip
						+ '</p><em class="p_arw p_arw_b" style="left:15px;bottom:-12px;"></em></div>';
				k += "</div>";
				k += "</div>";
				k += '<p class="gray font-f"> ' + a[j]["distance"] + "</p>";
				k += '<div class="nlist">' + ("" || "较少") + "</div>";
				k += '<a href="javascript:void(0);" onclick="return false;" data-type="'
						+ j + '" class="btn_detail show_prop">查看详情</a>';
				k += "</div>";
				k += '<div class="nright lifeMap fr show_prop" data-type="' + j
						+ '"></div>';
				k += "</div>";
				h += '<li id="' + j + '" data-attr="' + j + '">' + a[j].name
						+ "</li>"
			}
		}
		c.length && c.html(k);
		e.length && e.html(h)
	}
	function f(o, k, h, m) {
		var p = [];
		var l = [];
		for (var n in k) {
			if (k[n] != 0) {
				p.push("<p>" + k[n] + a[o]["attr"][n] + "</p>");
				if (n == "nursery" || n == "metro_station"
						|| n == "level_three" || n == "restaurant") {
					l.push('<span class="ico1 lifex" data-type="' + o
							+ '"></span>')
				}
				if (n == "bus_station" || n == "junior" || n == "level_tow"
						|| n == "bank") {
					l.push('<span class="ico2 lifex" data-type="' + o
							+ '"></span>')
				}
				if (n == "middlel" || n == "level_one" || n == "supermarket") {
					l.push('<span class="ico3 lifex" data-type="' + o
							+ '"></span>')
				}
				if (n == "level_comm") {
					l.push('<span class="ico4 lifex" data-type="' + o
							+ '"></span>')
				}
			}
		}
		h.html(p.slice(0, 3).join(""));
		m.html(l.slice(0, 3).join(""))
	}
	d()
});
function pslide(k) {
	var e = {}, o = {};
	o = J.mix(e, k);
	var p = o.idx, d = o.box, v = o.bps, b = o.sps, c = o.bConWidth, l = o.sConWidth, u = b
			.s("li a"), m = v.s(".picMove").eq(0), j = b.s(".picMove").eq(0), h = v
			.s(".btn_pre").eq(0), f = v.s(".btn_next").eq(0), q = 1, a, n;
	o.onIndexChange && J.on(document, "indexChange", function(w) {
				alert(w.data.index)
			});
	function r(F, D) {
		var G = F.s(".picMove").eq(0), H = F.s(".picMove li"), z = F
				.s(".picCon"), A = H.length, C = H.length * D, x = D
				|| z.get().width(), y = F.s(".btn_pre").eq(0), w = F
				.s(".btn_next").eq(0), E = 1;
		J.each(H, function(K, I) {
					I.attr("jsvalue", K + 1);
					I.jsvalue = K + 1
				});
		if (C > x) {
			var B = Math.ceil(C / x);
			if (B == 2) {
				w.get().style.visibility = "visible";
				w.on("click", function(I) {
							E = 1;
							g(F, G, E, x);
							w.get().style.visibility = "hidden";
							y.get().style.visibility = "visible";
							t(F, u, E, 1)
						});
				y.on("click", function(I) {
							E = 0;
							g(F, G, E, x);
							w.get().style.visibility = "visible";
							y.get().style.visibility = "hidden";
							t(F, u, E, 0)
						});
				s(F, false)
			} else {
				y.get().style.visibility = "visible";
				w.get().style.visibility = "visible";
				first = H.eq(0).get(), last = H.eq(A - 1).get();
				H = F.s(".picMove li"), A = H.length;
				G.get().style.width = x * A + "px";
				G.get().style.left = "-" + (E - 1) * x + "px";
				y.on("click", function(L) {
							q = E;
							E--;
							var I = H.length;
							if (F == b && n) {
								n && n--;
								if (n == 0) {
									n = I;
									g(F, G, n, x)
								} else {
									g(F, G, n, x)
								}
								return
							} else {
								if (E > 0) {
									g(F, G, E, x)
								}
							}
							if (a) {
								E = a;
								E--;
								if (E != 0) {
									G.get().style.left = "-" + (E - 1) * x
											+ "px"
								}
								var I = H.length;
								if (room_in_count == 0 || room_out_count == 0) {
									if (E == 0) {
										E = I;
										g(F, G, E - 1, x)
									}
								} else {
									if (E < 1) {
										J.g("photoSlide").s(".tabs a.next")
												.eq(0).get().click();
										var K = getTabParams("tnow");
										var I = K.spsLi.length;
										F = K.picBox;
										u = K.spsLi;
										K.spsLi.eq(I - 1).get().click();
										E = I
									} else {
										var K = getTabParams("tnow");
										F = K.picBox;
										u = K.spsLi
									}
								}
								a = E
							} else {
								if (room_in_count == 0 || room_out_count == 0) {
									if (E == 0) {
										E = I;
										g(F, G, E - 1, x)
									}
								} else {
									if (E < 1) {
										J.g("photoSlide").s(".tabs a.next")
												.eq(0).get().click();
										var K = getTabParams("tnow");
										var I = K.spsLi.length;
										F = K.picBox;
										u = K.spsLi;
										K.spsLi.eq(I - 1).get().click();
										E = I
									} else {
										var K = getTabParams("tnow");
										F = K.picBox;
										u = K.spsLi
									}
								}
							}
							t(F, u, E - 1, 0)
						});
				w.on("click", function(L) {
							q = E;
							E++;
							var I = H.length;
							if (F == b && n) {
								n && n++;
								if (n == I + 1) {
									n = 1;
									g(F, G, n, x)
								} else {
									g(F, G, n, x)
								}
								return
							} else {
								g(F, G, E - 1, x)
							}
							if (a) {
								E = a;
								E++;
								G.get().style.left = "-" + (E - 1) * x + "px";
								var I = H.length;
								if (room_in_count == 0 || room_out_count == 0) {
									if (E == I + 1) {
										E = 1;
										g(F, G, E - 1, x)
									}
								} else {
									if (E > I) {
										J.g("photoSlide").s(".tabs a.next")
												.eq(0).get().click();
										var K = getTabParams("tnow");
										F = K.picBox;
										u = K.spsLi;
										K.spsLi.eq(0).get().click();
										E = 1
									} else {
										var K = getTabParams("tnow");
										F = K.picBox;
										u = K.spsLi
									}
								}
								a = E
							} else {
								if (room_in_count == 0 || room_out_count == 0) {
									if (E == I + 1) {
										E = 1;
										g(F, G, E - 1, x)
									}
								} else {
									if (E > I) {
										J.g("photoSlide").s(".tabs a.next")
												.eq(0).get().click();
										var K = getTabParams("tnow");
										F = K.picBox;
										u = K.spsLi;
										K.spsLi.eq(0).get().click();
										E = 1
									} else {
										var K = getTabParams("tnow");
										F = K.picBox;
										u = K.spsLi
									}
								}
							}
							t(F, u, E - 1, 1)
						});
				s(F, true, u)
			}
		}
	}
	function s(x, w, y) {
		y && y.each(function(A, z) {
					var A = w ? A + 1 : A;
					z.get().setAttribute("jsvalue", A);
					z.jsvalue = A;
					z.eq(0).on("click", function(D) {
						D.preventDefault();
						a = z.jsvalue;
						if ((x != b) && !w) {
							var B = v.s(".btn_next").eq(0), C = v.s(".btn_pre")
									.eq(0);
							if (a == 1) {
								B.get().style.visibility = "hidden";
								C.get().style.visibility = "visible"
							} else {
								B.get().style.visibility = "visible";
								C.get().style.visibility = "hidden"
							}
						}
						liCur = !w ? a : a - 1;
						t(x, y, liCur, w);
						g(v, m, a - 1, c);
						q = a;
						n = z.up(0).get().jsvalue
					})
				})
	}
	function t(C, z, x, y) {
		if (C == b) {
			return
		}
		z.each(function(E, D) {
					z.eq(E) && z.eq(E).removeClass("now")
				});
		z.eq(x) && z.eq(x).addClass("now");
		var B = parseInt((x + 1) / 10) + 1;
		var A = b.s(".btn_next").eq(0), w = b.s(".btn_pre").eq(0);
		if (y == 1) {
			if (z.length >= 10) {
				var B = z.length > 20 ? B : B - 1;
				if (x % 10 != 9) {
					g(b, b.s(".picMove").eq(0), B, l)
				} else {
					g(b, b.s(".picMove").eq(0), B - 1, l)
				}
				if (z.length > 20 || z.length == 10) {
					return
				}
				if (B == 1) {
					A.get().style.visibility = "hidden";
					w.get().style.visibility = "visible"
				}
				if (x == 0) {
					A.get().style.visibility = "visible";
					w.get().style.visibility = "hidden"
				}
			} else {
			}
		} else {
			if (y == 0) {
				if (z.length >= 10) {
					var B = z.length > 20 ? B : B - 1;
					if (x % 10 != 9) {
						g(b, b.s(".picMove").eq(0), B, l)
					} else {
						g(b, b.s(".picMove").eq(0), B - 1, l)
					}
					if (z.length > 20 || z.length == 10) {
						return
					}
					if (B == 0 || x < 10) {
						A.get().style.visibility = "visible";
						w.get().style.visibility = "hidden"
					}
					if (x >= 10) {
						A.get().style.visibility = "hidden";
						w.get().style.visibility = "visible"
					}
				} else {
					g(b, b.s(".picMove").eq(0), B - 1, l)
				}
			}
		}
	}
	function g(y, w, z, x) {
		w.get().style.left = "-" + z * x + "px"
	}
	r(v, c);
	r(b, l);
	J.ready(function() {
				var w = v.s("img");
				w.each(function(A, x) {
							var z = w.eq(A), y = z.attr("data-src");
							y && z.attr("src", y).attr("data-src", "")
						})
			})
}
J.s(".tabscon").each(function(d, c) {
			var e = J.s(".tabscon").eq(d);
			if (e.s(".photoslide").length != 2) {
				return
			}
			var b = e.s(".photoslide").eq(0), a = e.s(".photoslide").eq(1);
			if (!b || !a) {
				return
			}
			pslide({
						box : e,
						bps : b,
						sps : a,
						bConWidth : 600,
						sConWidth : 830,
						onIndexChange : function() {
						}
					})
		});
var tabbox = J.g("photoSlide");
tabview(tabbox.s(".tabs a"), tabbox.s(".tabscon"));
function getTabParams(b) {
	var a = J.g("photoSlide").s(".con").eq(0).s("." + b).eq(0).s(".photoslide");
	return {
		picBox : a.eq(0),
		spsLi : a.eq(1).s("li a")
	}
}
function tabview(b, a) {
	var e = b.length, d = a.length, c = 0;
	if (e !== d) {
		return
	}
	b.each(function(g, f) {
				f.on("click", function(j) {
							j.preventDefault();
							if (f.hasClass("now")) {
								return
							} else {
								J.site.trackEvent("room_click_" + g);
								f.addClass("now");
								b.eq(c).removeClass("now");
								b.eq(c).addClass("next");
								b.eq(g).removeClass("next");
								a.eq(g).show();
								a.eq(g).addClass("tnow");
								a.eq(c).removeClass("tnow");
								a.eq(c).addClass("next");
								a.eq(g).removeClass("next");
								a.eq(c).hide();
								if (room_in_count != 0 && room_out_count != 0) {
									var h = getTabParams("next");
									h.spsLi.eq(0).get().click()
								}
								c = g
							}
						})
			})
}
J.ready(function() {
	if (window.isSHowEstateKnowledge) {
		a();
		b()
	}
	function a() {
		var d = "/api/fangyuanquestion/?comm_id=" + comm_info.comm_id
				+ "&comm_name=" + comm_info.comm_name;
		J.get({
					type : "json",
					url : d,
					onSuccess : function(e) {
						c(e)
					}
				});
		function c(l) {
			var f = !!J.s(".hotDiv").length ? J.s(".hotDiv").eq(0) : "", g = !!J
					.s(".relaDiv").length ? J.s(".relaDiv").eq(0) : "", k = !!J
					.s(".relaDiv_box").length ? J.s(".relaDiv_box").eq(0) : "", j = !!J
					.s(".related_lore").length
					? J.s(".related_lore").eq(0)
					: "";
			var e = "", h = "";
			if (l.host_questions && !!l.host_questions.length && f) {
				l.host_questions.each(function(o, n) {
							e += '<a href="/ask/view/' + o.QID + "?from="
									+ question_from + '" target="_blank">'
									+ o.TITLE + "</a>"
						});
				f.html(e)
			}
			if (l.relate_questions && !!l.relate_questions.length && k) {
				j.hide();
				l.relate_questions.each(function(o, n) {
							h += '<a href="/ask/view/' + o.QID + "?from="
									+ question_from + '" target="_blank">'
									+ o.TITLE + "</a>"
						});
				g.html(h);
				k.show()
			} else {
				k.hide();
				var m = !!J.s(".specialLink").length ? J.s(".specialLink")
						.eq(0) : "";
				(m && l.comm_baike.url)
						? m.attr("href",
								l.comm_baike.url + "?from=" + question_from)
								.attr("title", l.comm_baike.title)
								.html(l.comm_baike.title)
						: null;
				j.show()
			}
		}
	}
	function b() {
		var d = J.g("qa_txt"), e = J.g("qa_btn_tw");
		var c = "在这儿输入您的问题";
		d.val(d.attr("defval"));
		d.on("focus", function() {
					d.val() === "在这儿输入您的问题" ? d.val("") : "";
					d.addClass("qa_txt_hover")
				}).on("blur", function() {
					d.val() === "" ? d.val(d.attr("defval")) : "";
					d.removeClass("qa_txt_hover")
				});
		e.on("click", function() {
					var f = d.val() === c ? "" : d.val();
					window.open("http://" + J.g("hideUrl").val()
							+ "W0QQquestion_titleZ" + encodeURI(f)
							+ "QQfrommoduleZpropview")
				})
	}
});
var myScroll = (function() {
	var c = null, h = 0, j = 0;
	var e = [];
	var d = function(n, k, m) {
		if (typeof(n) == "string") {
			n = document.getElementById(n)
		}
		if (!n || n.scrollHeight <= n.clientHeight || n.clientHeight == 0) {
			return
		}
		n.scrollBarWidth = k || 6;
		n.style.overflow = "hidden";
		n.scrollBar = document.createElement("div");
		n.appendChild(n.scrollBar);
		n.scrollBarIndex = document.createElement("div");
		n.scrollBar.appendChild(n.scrollBarIndex);
		n.scrollBar.style.position = "absolute";
		n.scrollBarIndex.style.position = "absolute";
		n.scrollBarIndex.id = "scrollBarIndex";
		n.scrollBar.className = m || "";
		if (!m) {
			n.scrollBar.style.backgroundColor = "#fff";
			n.scrollBarIndex.style.backgroundColor = "#dbdbdb"
		}
		e.push(n);
		g(n);
		n.scrollBar.scrollDiv = n;
		n.scrollBarIndex.scrollDiv = n;
		function l(p, o) {
			if (window.netscape) {
				p.addEventListener("DOMMouseScroll", o, false)
			} else {
				p.onmousewheel = o
			}
		}
		l(n, f);
		l(n.scrollBar, f);
		l(n.scrollBarIndex, f);
		n.scrollBarIndex.onmousedown = function(o) {
			o = o || event;
			h = o.clientY;
			j = this.scrollDiv.scrollTop;
			isScrollMove = true;
			document.body.onselectstart = function() {
				return false
			};
			c = this.scrollDiv;
			if (this.scrollDiv.scrollBar.className == "") {
				this.scrollDiv.scrollBarIndex.style.backgroundColor = "#dbdbdb"
			}
			return false
		}
	};
	var g = function(m) {
		if (m.scrollHeight <= m.clientHeight) {
			m.scrollTop = 0;
			m.scrollBar.style.display = "none"
		} else {
			m.scrollBar.style.display = "block"
		}
		var l = m;
		var n = parseInt(m.style.borderTopWidth || 0);
		var k = parseInt(m.style.borderBottomWidth || 0);
		m.scrollBar.style.width = m.scrollBarWidth + "px";
		m.scrollBar.style.height = m.clientHeight - 10 + "px";
		m.scrollBar.style.top = n + 10 + "px";
		m.scrollBar.style.right = 10 + "px";
		m.scrollBarIndex.style.width = m.scrollBarWidth + "px";
		m.scrollBarIndex.style.cursor = "pointer";
		m.scrollBarHeight = 69;
		m.scrollBarIndex.style.height = 69 + "px";
		m.scrollBarIndex.style.left = "0px";
		a(m)
	};
	var a = function(k) {
		k.scrollBarIndex.style.top = (k.clientHeight - 10 - k.scrollBarHeight)
				* k.scrollTop / (k.scrollHeight - k.clientHeight) + "px"
	};
	document.documentElement.onmousemove = function(k) {
		if (!c) {
			return
		}
		k = k || event;
		var l = (c.scrollHeight - c.clientHeight)
				/ (c.clientHeight - c.scrollBarHeight - 10);
		c.scrollTop = j - (h - k.clientY) * l;
		a(c)
	};
	document.documentElement.onmouseup = function(k) {
		if (!c) {
			return
		}
		if (c.scrollBar.className == "") {
			c.scrollBarIndex.style.backgroundColor = "#dbdbdb"
		}
		c = null;
		document.body.onselectstart = function() {
			return true
		}
	};
	var f = function(k) {
		if (k && k.stopPropagation) {
			k.stopPropagation()
		} else {
			window.event.cancelBubble = true
		}
		var m = this.scrollDiv || this;
		if (m.scrollHeight <= m.clientHeight) {
			return true
		}
		k = k || event;
		var l = 20;
		if (k.wheelDelta < 0 || k.detail > 0) {
			if (m.scrollTop >= (m.scrollHeight - m.clientHeight)) {
				return true
			}
			m.scrollTop += l
		} else {
			if (m.scrollTop == 0) {
				return true
			}
			m.scrollTop -= l
		}
		a(m);
		return false
	};
	var b = function(l, k) {
		if (l.scrollHeight > l.clientHeight) {
			l.scrollTop = k.offsetTop - l.clientHeight + 39;
			a(l)
		}
	};
	return {
		jsScroll : d,
		jumpTo : b
	}
})();
var areaMap = {};
J.ready(function() {
	areaMap = (function() {
		var a = function(p) {
			var g = [{
						featureType : "water",
						elementType : "all",
						stylers : {
							color : "#dceaf7"
						}
					}, {
						featureType : "green",
						elementType : "all",
						stylers : {
							color : "#d9ead3"
						}
					}, {
						featureType : "manmade",
						elementType : "all",
						stylers : {
							color : "#f2ebe5"
						}
					}, {
						featureType : "highway",
						elementType : "all",
						stylers : {
							color : "#ffffff"
						}
					}, {
						featureType : "highway",
						elementType : "geometry.stroke",
						stylers : {
							color : "#d7d0c7"
						}
					}, {
						featureType : "highway",
						elementType : "labels.text.fill",
						stylers : {
							color : "#aca481"
						}
					}, {
						featureType : "highway",
						elementType : "labels.text.stroke",
						stylers : {
							color : "#f2ebe5"
						}
					}, {
						featureType : "arterial",
						elementType : "all",
						stylers : {
							color : "#ffffff"
						}
					}, {
						featureType : "arterial",
						elementType : "labels.text.fill",
						stylers : {
							color : "#aea9a9"
						}
					}, {
						featureType : "arterial",
						elementType : "labels.text.stroke",
						stylers : {
							color : "#fbf2d9"
						}
					}, {
						featureType : "local",
						elementType : "all",
						stylers : {
							color : "#ffffff"
						}
					}, {
						featureType : "local",
						elementType : "labels.text.fill",
						stylers : {
							color : "#999999"
						}
					}, {
						featureType : "subway",
						elementType : "geometry",
						stylers : {
							lightness : 50
						}
					}, {
						featureType : "subway",
						elementType : "labels",
						stylers : {
							lightness : 70
						}
					}, {
						featureType : "poi",
						elementType : "labels",
						stylers : {
							lightness : 45
						}
					}, {
						featureType : "label",
						elementType : "labels.text.fill",
						stylers : {
							color : "#c3b2a0"
						}
					}, {
						featureType : "boundary",
						elementType : "geometry",
						stylers : {
							color : "#f1c232"
						}
					}];
			var e = {
				lng : comm_info.comm_lng,
				lat : comm_info.comm_lat,
				id : "map_container",
				zoom : 16,
				minz : 11,
				maxz : 18,
				nav : false,
				name : comm_info.comm_name
			}, r, y, q, m, n, x, t, l, b, z, v, s = 0, h, w;
			j(u);
			function u() {
				z = J.g("mappop_content");
				q = J.g("map_container");
				v = J.g("mappop_content").s("h5").eq(0);
				v.html(comm_info.comm_name);
				h = J.g("map_tab_title").s("li");
				w = h.length;
				r = J.mix(e, p || {});
				y = new J.map.bmap(r);
				y.enableScrollWheelZoom();
				y.setMapStyle(g);
				f();
				k();
				d();
				window.onresize = function() {
					d()
				};
				var A = new o();
				m = A.Data;
				x = A.View;
				n = A.Event;
				t = new BMap.Point(r.lng, r.lat);
				c()
			}
			function j(B) {
				var A = "2.0";
				(function() {
					B();
					y.moveToCenter = function() {
						y.setCenter(r.lng, r.lat, r.zoom)
					}
				}).require("map.Bmap", "")
			}
			function d() {
				var A = document.documentElement.clientHeight
						|| document.body.clientHeight;
				if (A < 700) {
					z.removeClass("mappop_content");
					z.addClass("mappop_content_small");
					var B = document.documentElement.clientHeight - 317;
					if (B > 0) {
						J.g("map_container").get().style.height = B + "px"
					}
				} else {
					z.removeClass("mappop_content_small");
					z.addClass("mappop_content");
					J.g("map_container").get().style.height = "340px"
				}
			}
			function f() {
				var C = J.g("map_close");
				var D = J.g("mappop-content-background");
				var B = J.g("mappop_content");
				var A = J.g("mappop-iframe");
				J.g(document).on("click", function(H) {
					var H = H || window.event;
					var G = H.target || H.srcElement;
					if ((("string" === typeof G.className) && (G.className
							.indexOf("show_prop") > -1))
							|| (("string" === typeof G.className) && (G.className
									.indexOf("lifex") > -1))) {
						E(J.g(G));
						document.body.style.overflow = "hidden";
						J.s("html").eq(0).setStyle({
									overflow : "hidden"
								});
						var F = J.g(G).attr("data-type");
						J.g(F).get().click()
					}
				});
				C.length && C.on("click", function() {
							A && A.setStyle({
										display : "none"
									});
							D && D.setStyle({
										display : "none"
									});
							B && B.setStyle({
										display : "none"
									});
							document.body.style.overflow = "auto";
							J.s("html").eq(0).setStyle({
										overflow : "auto"
									})
						});
				function E(I) {
					J.site.trackEvent("showMapPop");
					var G = 0;
					var L = J.g("mappop-content-background");
					var H = J.g("mappop_content");
					var K = I.attr("data-type");
					for (var F = 0; F < w; F++) {
						h.eq(F).removeClass("active")
					}
					for (var F = 0; F < w; F++) {
						if (h.eq(F).attr("data-attr") == K) {
							h.eq(F).addClass("active")
						}
					}
					if (J.g("mappop-content").length
							&& J.g("mappop-content-background")) {
						J.g("mappop-content").remove();
						J.g("mappop-content-background").remove()
					}
					A && A.setStyle({
								display : "block"
							});
					L.setStyle({
								display : "block"
							});
					H.setStyle({
								display : "block"
							})
				}
			}
			function c() {
				var A = J.mix({}, r);
				A.x = -22;
				A.y = -42;
				A.zIndex = 2, A.className = "overlay_comm";
				A.html = '<div class="font_14">' + r.name
						+ '<span class="icon_map tip"></span></div>';
				y.setCenter(A.lng, A.lat, A.zoom);
				y.addOverlay(A)
			}
			function k() {
				var C;
				var A;
				var B = y;
				C = z.s(".btn_zoomin").eq(0);
				A = z.s(".btn_zoomout").eq(0);
				C.on("click", function() {
							B.getMap().zoomIn()
						});
				A.on("click", function() {
							B.getMap().zoomOut()
						});
				h.each(function(E, D) {
							D.on("click", function(F) {
										h.each(function(H, G) {
													G.removeClass("active");
													n.tabCancled.call(this, H)
												});
										D.addClass("active");
										n.tabclicked.call(this, F)
									})
						})
			}
			function o() {
				var E = {
					traffic : {
						target : null,
						nodes : {},
						overlays : [],
						data : null
					},
					school : {
						target : null,
						nodes : {},
						overlays : [],
						data : null
					},
					hospital : {
						target : null,
						nodes : {},
						overlays : [],
						data : null
					},
					commerce : {
						target : null,
						nodes : {},
						overlays : [],
						data : null
					}
				}, B, D, G, C;
				function I() {
					B = new F();
					D = new A();
					G = new H();
					C = J.g("tab_content")
				}
				I();
				function A() {
					function K(W, Y) {
						var V, U, X;
						var Z = [];
						U = E[W].nodes;
						V = document.createElement("ul");
						V.className = "item" + W;
						V.id = "map_tab_content";
						J.each(Y, function(ac, ab) {
							var aa, ad;
							ad = ab.distance + "米";
							aa = document.createElement("li");
							aa.className = "map_" + W;
							aa.innerHTML = '<i class="icon_map tip"></i><div class="map_list_left"><a>'
									+ ab.title
									+ "</a>"
									+ (!ab.address ? "" : "&nbsp;-&nbsp;<span>"
											+ ab.address + "</span>")
									+ '</div><em class="distance">'
									+ ad
									+ "</em>";
							J.g(aa).on("mouseenter", G.listItemMouseOver);
							J.g(aa).on("mouseleave", G.listItemMouseOut);
							T(W, ab, aa);
							Z.push(ab.point);
							V.appendChild(aa)
						});
						Z.push(t);
						E[W].viewport = y.getViewport(Z);
						y.setViewport(E[W].viewport);
						V.style.display = "block";
						C.html(V);
						if (J.g(V).html() == "") {
							J.g(V).html('<span class="none_info">暂无数据</span>')
						}
						return V
					}
					function T(X, V, U) {
						if (!V.point) {
							return
						}
						V.className = "overlay";
						V.html = '<div class="icon_map ' + X + '"></div>';
						V.lat = V.point.lat;
						V.lng = V.point.lng;
						V.x = -14;
						V.y = -37;
						V.zIndex = 1;
						var W = y.addOverlay(V);
						U.relatedTarget = W;
						W.relatedTarget = U;
						W.onMouseOver = G.listItemMouseOver;
						W.onMouseOut = G.listItemMouseOut;
						if (X == "sub" || X == "bus") {
							E.traffic.overlays.push(W)
						} else {
							if (X == "restaurant" || X == "bank"
									|| X == "supermarket") {
								E.commerce.overlays.push(W)
							} else {
								E[X].overlays.push(W)
							}
						}
					}
					function O(U, Z) {
						var Y, V, ac, ad, W;
						var ab = [];
						var aa = "traffic";
						var ae = "sub";
						var X = "bus";
						Y = document.createElement("ul");
						Y.className = "item" + aa;
						Y.id = "map_tab_content";
						V = E[aa].nodes;
						N(aa, U, ae, Y, ab);
						N(aa, Z, X, Y, ab);
						E[aa].viewport = y.getViewport(ab);
						y.setCenter(r.lng, r.lat, E[aa].viewport.zoom);
						C.html(Y);
						if (J.g(Y).html() == "") {
							J.g(Y).html('<span class="none_info">暂无数据</span>')
						}
						return Y
					}
					function P(W, aa, X) {
						var Z, U, ae, ag, V;
						var ac = "commerce";
						var af = "restaurant";
						var Y = "bank";
						var ab = "supermarket";
						var ad = [];
						Z = document.createElement("ul");
						Z.className = "item" + ac;
						Z.id = "map_tab_content";
						U = E[ac].nodes;
						N(ac, W, af, Z, ad);
						N(ac, aa, Y, Z, ad);
						N(ac, X, ab, Z, ad);
						E[ac].viewport = y.getViewport(ad);
						y.setCenter(r.lng, r.lat, E[ac].viewport.zoom);
						C.html(Z);
						if (J.g(Z).html() == "") {
							J.g(Z).html('<span class="none_info">暂无数据</span>')
						}
						return Z
					}
					function N(W, V, Y, U, X) {
						J.each(V, function(aa, Z) {
							distance = Z.distance + "米";
							li = document.createElement("li");
							li.className = "map_" + Y;
							li.innerHTML = '<i class="icon_map tip"></i><div class="map_list_left"><a>'
									+ Z.title
									+ "</a>"
									+ (!Z.address ? "" : "&nbsp;-&nbsp;<span>"
											+ Z.address + "</span>")
									+ '</div><em class="distance">'
									+ distance
									+ "</em>";
							J.g(li).on("mouseenter", G.listItemMouseOver);
							J.g(li).on("mouseleave", G.listItemMouseOut);
							T(Y, Z, li);
							X.push(Z.point);
							U.appendChild(li)
						})
					}
					function R(W, U) {
						var V = "traffic";
						var X;
						E[V].target = X = O(W, U)
					}
					function S(V) {
						var U = "school";
						var W;
						E[U].target = W = K(U, V)
					}
					function Q(V) {
						var U = "hospital";
						var W;
						E[U].target = W = K(U, V)
					}
					function M(W, U, V) {
						var X = "commerce";
						var Y;
						E[X].target = Y = P(W, U, V)
					}
					var L = {
						getTraffic : R,
						getSchool : S,
						getHospital : Q,
						getCommerce : M
					};
					return L
				}
				function H() {
					var K = t;
					function P() {
						var S, R;
						if (!this.nodeType) {
							S = this.relatedTarget;
							R = this
						} else {
							S = this;
							R = this.relatedTarget
						}
						J.g(S).addClass("maphover");
						R._div.addClass("maphover");
						R._div.setStyle({
									zIndex : "3"
								});
						if (!this.nodeType) {
							J.g(S).length
									&& J.g("map_tab_content")
									&& myScroll.jumpTo(J.g("map_tab_content")
													.get(), J.g(S).get())
						}
						if (!R.infoWindow) {
							var T = J.mix({}, R.p);
							T.html = '<div class="overlay_container"><span class="icon_map tip"></span><b>'
									+ T.title
									+ "</b><div>"
									+ T.distance
									+ "米，步行"
									+ parseInt(T.distance / 75)
									+ "分钟</div></div>";
							T.className = "overlay_info";
							if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
								T.y = -101;
								T.x = -25
							} else {
								T.y = -101;
								T.x = -35
							}
							T.zIndex = 10000;
							R.infoWindow = y.addOverlay(T);
							L(R.infoWindow, T);
							return
						}
						R.infoWindow.show();
						L(R.infoWindow, R.infoWindow.p)
					}
					function Q() {
						var S, R;
						if (!this.nodeType) {
							S = this.relatedTarget;
							R = this
						} else {
							S = this;
							R = this.relatedTarget
						}
						J.g(S).removeClass("maphover");
						R._div.removeClass("maphover");
						R._div.setStyle({
									zIndex : "1"
								});
						R.infoWindow && R.infoWindow.hide()
					}
					function M() {
						var R = this.id || this.attr("id")
								|| this.attr("data-type");
						if (E[R].target) {
							C.html(E[R].target);
							J.each(E[R].overlays, function(U, T) {
										T.show()
									});
							J.g("scrollBarIndex")
									&& J.g("scrollBarIndex").setStyle({
												top : "0px"
											})
						} else {
							y.setCenter(r.lng, r.lat, r.zoom);
							var S = y.getCenter();
							R = "get" + R.charAt(0).toUpperCase()
									+ R.substring(1);
							B[R](function(T) {
								if (Array.prototype.slice.call(arguments).length == 0) {
									C
											.html('<span class="none_info">暂无数据</span>')
								} else {
									D[R].apply(this, Array.prototype.slice
													.call(arguments))
								}
								J.g("map_tab_content")
										&& myScroll.jsScroll(J
												.g("map_tab_content").get())
							})
						}
					}
					function O() {
						var R = this.attr("id") || this.attr("data-type");
						E[R].target && J.g(E[R].target).remove();
						J.each(E[R].overlays, function(T, S) {
									S.hide()
								})
					}
					function L(U, ak) {
						var R = y.getMapWH(), aj = y.pointToPixel(ak.latlng), V = parseInt(aj.y), W = parseInt(aj.x);
						var ad = W + ak.x;
						var S = V + ak.y;
						var T = {
							x : ad,
							y : S
						};
						var X = U.get().width();
						var ac = U.get().height();
						var af = {
							x : ad + X,
							y : V
						};
						var ai = 0;
						var ag = 0;
						var ah = 0;
						if (T.x < 0) {
							ai = T.x
						}
						if ((af.x - R.width) > 0) {
							ai = af.x - R.width
						}
						if (T.y < 0) {
							ag = T.y
						}
						if ((af.y - R.height) > 0) {
							ag = af.y - R.height
						}
						var ab = y.getCenter();
						var ae = y.pointToPixel(ab);
						var aa = ae.x;
						var Y = ae.y;
						aa = aa + ai;
						Y = Y + ag;
						var Z = y.pixelToPoint(new BMap.Pixel(aa, Y));
						y.getMap().setCenter(Z)
					}
					function N() {
						J.each(E, function(S, R) {
									R.target = null;
									R.nodes = {};
									R.overlays = []
								})
					}
					return {
						listItemMouseOver : P,
						listItemMouseOut : Q,
						tabclicked : M,
						tabCancled : O,
						clearCache : N,
						infoWindowToView : L
					}
				}
				function F() {
					function P(S, T) {
						y.localSearchNearby(S, function(U) {
							J.each(U, function(W, V) {
								V.address = V.address == V.undefined
										? ""
										: V.address;
								V.distance = parseInt(y.getDistance(t, V.point))
							});
							U.sort(function(W, V) {
										if (W.distance < V.distance) {
											return -1
										}
										if (W.distance == V.distance) {
											return 0
										}
										if (W.distance > V.distance) {
											return 1
										}
									});
							T(U)
						}, 10, 1000)
					}
					function O(S, T) {
						y.localSearchNearby(S, function(U) {
							J.each(U, function(W, V) {
								V.address = V.address == V.undefined
										? ""
										: V.address;
								V.distance = parseInt(y.getDistance(t, V.point))
							});
							U.sort(function(W, V) {
										if (W.distance < V.distance) {
											return -1
										}
										if (W.distance == V.distance) {
											return 0
										}
										if (W.distance > V.distance) {
											return 1
										}
									});
							T(U)
						}, 10, 5000)
					}
					function R(S, T, U) {
						for (i in S) {
							if (S[i].distance < U) {
								T.push(S[i])
							}
						}
						return T
					}
					function Q(Y) {
						type = "traffic";
						var T, U;
						var X = {
							busData : null,
							subData : null
						};
						function W(ac) {
							var aa = [], ae = [], ad = [];
							R(ac.busData, ae, 1000);
							R(ac.subData, ad, 1000);
							var Z = ae.length + ad.length;
							if (ad.length > 5) {
								var ab = ad.length > 5 ? 5 : ad;
								ab = ad.slice(0, ab);
								T = ae.slice(0, 5 - ab)
							} else {
								ab = ad;
								T = ae.slice(0, 10 - ad.length)
							}
							aa = Y(ab, T)
						}
						function S(Z) {
							X.subData = Z;
							if (X.subData && X.busData) {
								E[type].data = X;
								W(X)
							}
						}
						function V(Z) {
							X.busData = Z;
							if (X.subData && X.busData) {
								E[type].data = X;
								W(X)
							}
						}
						P("地铁", S);
						P("公交", V)
					}
					function L(U) {
						var T = "school";
						var S = [];
						E[T].data && U(E[T].data);
						P("学校", function(V) {
									R(V, S, 1000);
									E[T].data = S;
									U(S)
								})
					}
					function N(U) {
						var S = "hospital";
						var T = [];
						E[S].data && U(E[S]);
						O("医院", function(V) {
									R(V, T, 5000);
									E[S].data = T;
									U(T)
								})
					}
					function K(ac) {
						var aa = "commerce";
						var W, Y, X;
						var V = {
							restaurantData : null,
							bankData : null,
							supermarketData : null
						};
						function Z(ai) {
							var ad = [], aj = [], ah = [];
							R(ai.restaurantData, ad, 1000);
							R(ai.bankData, aj, 1000);
							R(ai.supermarketData, ah, 1000);
							var af = [];
							var ae = ad.length + aj.length + ah.length;
							if (ae > 10) {
								var ag = ad.length > 6 ? 6 : ad.length;
								var ak = aj.length > 2 ? 2 : aj.length;
								W = ad.slice(0, ag);
								Y = aj.slice(0, ak);
								X = ah.slice(0, 10 - ag - ak)
							} else {
								W = ad;
								Y = aj;
								X = ah
							}
							af = ac(W, Y, X)
						}
						function U() {
							if (V.restaurantData && V.bankData
									&& V.supermarketData) {
								E[aa].data = V;
								Z(V)
							}
						}
						function T(ad) {
							V.restaurantData = ad;
							U()
						}
						function ab(ad) {
							V.bankData = ad;
							U()
						}
						function S(ad) {
							V.supermarketData = ad;
							U()
						}
						P("餐馆", T);
						P("银行", ab);
						P("超市", S)
					}
					var M = {
						getTraffic : Q,
						getSchool : L,
						getHospital : N,
						getCommerce : K
					};
					return M
				}
				return {
					View : D,
					Event : G,
					Data : B
				}
			}
			return {
				map : y
			}
		};
		return {
			AreaBlock : a
		}
	})()
});
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "")
};
String.prototype.chkType = function(a) {
	switch (a) {
		case "int" :
			return (/^-?[1-9][0-9]+$|^-?[0-9]$/).test(this);
		case "url" :
			return (/^https?:\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/).test(this);
		case "email" :
			return (/^[a-z0-9_+.-]+\@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/i)
					.test(this);
		case "idcard" :
			return (/^[0-9]{15}$|^[0-9]{17}[a-zA-Z0-9]/).test(this);
		case "area" :
			return (/^\d+(\.\d{1,2})?$/).test(this);
		case "money" :
			return (/^\d+(\.\d{1,2})?$/).test(this);
		case "year" :
			return (/^(19|20)\d\d$/).test(this);
		case "input" :
			return (/^[\u4e00-\u9fa5A-Za-z0-9_\s\~\@\!\#\$\.\,\/\\\%\^\&\*\(\)_\+\?\>\<《〉》\:：〉、，。？！￥（）\{\}\[\]]+$/)
					.test(this)
	}
	return false
};
String.prototype.containType = function(a) {
	switch (a) {
		case "mobile" :
			return (/[0-9]{11}/).test(this)
	}
	return false
};
String.prototype.dbcToSbc = function() {
	var a = "";
	for (var b = 0; b < this.length; b++) {
		if (this.charCodeAt(b) == 12288) {
			a += String.fromCharCode(this.charCodeAt(b) - 12256);
			continue
		}
		if (this.charCodeAt(b) > 65280 && this.charCodeAt(b) < 65375) {
			a += String.fromCharCode(this.charCodeAt(b) - 65248)
		} else {
			a += String.fromCharCode(this.charCodeAt(b))
		}
	}
	return a
};
Array.prototype.unique = function() {
	var c = {};
	for (var b = 0, a = 0; b < this.length; b++) {
		if (this[b] !== undefined) {
			if (!c[this[b]]) {
				this[a++] = this[b];
				c[this[b]] = true
			}
		}
	}
	this.length = a;
	return this
};
Array.prototype.clone = function() {
	var a = [];
	for (var b in this) {
		if (a[b] === undefined && typeof this[b] == "string") {
			a[b] = this[b]
		}
	}
	return a
};
Array.prototype.insertAt = function(a, c) {
	var d = this.slice(0, a);
	var b = this.slice(a);
	d.push(c);
	return (d.concat(b))
};
Array.prototype.each = function(b) {
	if (!this.length) {
		return
	}
	if ([].forEach) {
		[].forEach.call(this, b)
	} else {
		for (var a = 0; a < this.length; a++) {
			b.call(this[a], this[a], a)
		}
	}
};
window.openNew = function(c, a) {
	var b = document.createElement("form");
	form.action = c;
	form.target = a;
	form.submit()
};
window.getRadioValue = function(b) {
	var c = document.getElementsByName(b);
	var d = c.length;
	if (0 == d) {
		return null
	}
	for (var a = 0; a < d; ++a) {
		if (c[a].checked == true) {
			return c[a].value
		}
	}
	return ""
};
window.cancelBubble = function(a) {
	if (a && a.stopPropagation) {
		a.stopPropagation()
	} else {
		window.event.cancelBubble = true
	}
	return false
};
J.ready(function() {
			var a = {
				getImg : function(e, d) {
					var f = e.getAttribute(d);
					e && d && f && (e.src = f)
				},
				getImgsByDom : function(h, e) {
					var j, g, d, f;
					if (h && e) {
						j = h.getElementsByTagName("img");
						for (g = 0, d = j.length; g < d; g++) {
							this.getImg(j[g], e)
						}
					}
				}
			};
			function c() {
				var d = d || [];
				window._hmt = d;
				J.load("//hm.baidu.com/hm.js?c5899c8768ebee272710c9c5f365a6d8")
			}
			function b() {
				a.getImgsByDom(document, "data-src");
				c()
			}
			b()
		});
document.body.oncopy = function() {
	alert("受保护的内容，暂不可复制！");
	event.returnValue = false
};
(function() {
	if (location.pathname.match(/prop\/view\//g)) {
		var a = 0;
		J.on(window, "scroll", function() {
					var b = document.documentElement.scrollTop
							|| document.body.scrollTop;
					a = Math.ceil(b / document.documentElement.clientHeight)
				});
		if (navigator.userAgent.toLowerCase().indexOf("opera") >= 0) {
			window.onunload = function() {
				J.site.trackEvent("Anjuke_View_Property_viewed_seeRange_"
						+ (a + 1))
			}
		} else {
			window.onbeforeunload = function(b) {
				J.site.trackEvent("Anjuke_View_Property_viewed_seeRange_"
						+ (a + 1))
			}
		}
	}
})();
J.ready(function() {
	var anchor = function() {
		var target, agent, agentDetail, targetIn, linkArray, linkArrayFix, docHeight;
		var hasShow, tabArray, len, curIndex, isALinkSkip;
		function getElements() {
			target = J.g("toolBarFix");
			agent = (J.s(".brokerInfo").length > 0) && J.s(".brokerInfo").eq(0);
			agentDetail = (J.s(".b_detail").length > 0)
					&& J.s(".b_detail").eq(0);
			targetIn = (J.s(".mainbox").length > 0) && J.s(".mainbox").eq(0);
			linkArrayFix = target.length && target.s(".toolMenu").eq(0).s("a");
			docHeight = Math.max(document.body.clientHeight,
					document.documentElement.clientHeight);
			curIndex = 0
		}
		function bindAction() {
			agent.length && agent.on("mouseenter", function() {
						agent.addClass("b_hover")
					});
			agent.length && agent.on("mouseleave", function() {
						agent.removeClass("b_hover")
					});
			agentDetail.length && agentDetail.on("mouseenter", function() {
						agent.addClass("b_hover")
					});
			agentDetail.length && agentDetail.on("mouseleave", function(e) {
				var e = window.event || e;
				var relatedElement = e.relatedTarget || e.toElement;
				if (J.g(relatedElement).attr("class")
						&& !J.g(relatedElement).attr("class").indexOf("bInfo") == -1) {
					agent.removeClass("b_hover")
				}
			});
			linkArrayFix.length && linkArrayFix.each(function(i, v) {
				v.on("click", function() {
							isALinkSkip = true;
							linkArrayFix.each(function(j, e) {
										e.removeClass("now")
									});
							v.addClass("now");
							J.site
									.trackEvent("Track_Anjuke_View_Property_Anchor_"
											+ i)
						})
			});
			if (targetIn.length) {
				if (window.addEventListener) {
					window.addEventListener("scroll", scrollCheck, false)
				} else {
					window.attachEvent("onscroll", scrollCheck)
				}
			}
		}
		function initTabArray() {
			var top = document.body.scrollTop
					|| document.documentElement.scrollTop;
			tabArray = [];
			linkArrayFix && linkArrayFix.each(function(i, v) {
						tabArray[i] = J.g(v.attr("href").replace(/[\s\S]*\#/g,
								"")).offset().y
								- 30
					})
		}
		function InitPosition() {
			var place = location.href.match(/\#\w*/g);
			if (place) {
				linkArrayFix.each(function(i, v) {
							if (v.attr("href") == place[0]) {
								curIndex = i;
								return
							}
						})
			}
			linkArrayFix.eq(curIndex).addClass("now");
			len = tabArray.length - 1
		}
		function scrollCheck() {
			if (isALinkSkip) {
				isALinkSkip = false;
				return
			}
			var top = document.body.scrollTop
					|| document.documentElement.scrollTop;
			var curHeight = Math.max(document.body.clientHeight,
					document.documentElement.clientHeight);
			if (curHeight != docHeight) {
				docHeight = curHeight;
				initTabArray()
			}
			if (!hasShow) {
				if (top >= targetIn.offset().y) {
					target.setStyle({
								display : "block"
							});
					hasShow = true
				}
			} else {
				if (top < targetIn.offset().y) {
					target.setStyle({
								display : "none"
							});
					hasShow = false
				}
				switchAnchorTab(top)
			}
		}
		function switchAnchorTab(top) {
			if (curIndex < len && top >= tabArray[curIndex + 1]) {
				linkArrayFix.eq(curIndex).removeClass("now");
				linkArrayFix.eq(curIndex + 1).addClass("now");
				curIndex = curIndex + 1
			} else {
				if (curIndex > 0 && top < tabArray[curIndex]) {
					linkArrayFix.eq(curIndex).removeClass("now");
					linkArrayFix.eq(curIndex - 1).addClass("now");
					curIndex = curIndex - 1
				}
			}
		}
		return {
			init : function(callback) {
				if (J.g("toolBarFix").length) {
					callback && callback();
					getElements();
					bindAction();
					initTabArray();
					InitPosition()
				}
			}
		}
	}();
	anchor.init(function() {
		if (J.g("refer_month").get().style.display == "none") {
			var toolbar = J.g("toolBarFix"), navfix = toolbar.s(".toolMenu li");
			navfix.eq(2).remove()
		}
	});
(function() {
		var getLinks = J.g("proLinks");
		if (getLinks.length != 0) {
			var param = {
				BrokerId : link_info.broker_id,
				ProId : link_info.prop_id,
				CommId : link_info.comm_id,
				searchUrl : link_info.prop_search_url_pre
			};
			J.get({
						url : "/ajax/ajaxviewpropsaley/",
						data : param,
						timeout : 20000,
						onSuccess : function(data) {
							obj = eval("(" + data + ")");
							if (obj.num == 0) {
								getLinks.hide()
							} else {
								var housetag = obj.housetag, xqutag = obj.xqutag;
								(housetag.length != 0 || xqutag.length != 0)
										? getLinks.show()
										: getLinks.hide();
								if (housetag.length != 0) {
									var houseHtml = [];
									housetag.each(function(v, i) {
												houseHtml.push('<a href="'
														+ param.searchUrl
														+ housetag[i]
														+ '" target="_blank">'
														+ housetag[i] + "</a>")
											});
									var houseDom = J.create("p").html(houseHtml
											.join(""));
									getLinks.eq(0).get().appendChild(houseDom
											.get())
								}
								if (xqutag.length != 0) {
									var xqHtml = [];
									xqutag.each(function(v, i) {
												xqHtml
														.push("<span>"
																+ xqutag[i]
																+ "</span>")
											});
									var xqDom = J.create("p").html(xqHtml
											.join(""));
									getLinks.eq(0).get().appendChild(xqDom
											.get())
								}
							}
						}
					})
		}
	})();
(function() {
		var box = J.g("propContent"), con = box.s(".pro_con").eq(0), arwBox = box
				.next(), conH = con.height();
		var boxH = box.height(), limit = 310;
		if (conH > limit) {
			arwBox.get().style.display = "block";
			var btn = arwBox.s(".btn_show").eq(0), ht = conH + "px", lt = limit
					+ "px";
			btn.on("click", function() {
				if (btn.hasClass("btn_all")) {
					box.get().style.height = ht;
					btn.removeClass("btn_all").addClass("btn_up");
					btn.attr("title", "收起全部");
					J.site
							.trackEvent("Track_Anjuke_SinglePageDescription_Unfold_0")
				} else {
					if (btn.hasClass("btn_up")) {
						box.get().style.height = lt;
						btn.removeClass("btn_up").addClass("btn_all");
						btn.attr("title", "显示全部");
						J.site
								.trackEvent("Track_Anjuke_SinglePageDescription_Collapse_0")
					}
				}
			})
		} else {
			arwBox.get().style.display = "none"
		}
	})();
(function() {
		var starbox = J.s(".p_star");
		starbox.each(function(i, v) {
					v.on("mouseenter", function(e) {
								var con = v.s(".p_arowCon").eq(0);
								con.show()
							}).on("mouseleave", function(e) {
								var con = v.s(".p_arowCon").eq(0);
								con.hide()
							})
				})
	})();
	(function() {
		var open_chat = J.s(".only_open_chat"), lastClickTime = 0, currClickTime = 0;
		open_chat.length && open_chat.each(function(e, v) {
			v.on("click", function() {
				if (lastClickTime == 0 || (currClickTime - lastClickTime) > 500) {
					J.chat.opener.open(link_info.broker_id, link_info.prop_id,
							link_info.chat_id, city_id)
				}
			})
		})
	}.require(["chat.opener"], true));
	J.get({
				url : history_url
			})
});
(function() {
	J.g("wxcode").on("mouseenter", function() {
				var a = J.g("broker-erweima");
				a.addClass("codeshow")
			});
	J.g("wxcode").on("mouseleave", function() {
				var a = J.g("broker-erweima");
				a.removeClass("codeshow")
			});
	J.g("wxcode").on("click", function(a) {
				return false
			})
})();