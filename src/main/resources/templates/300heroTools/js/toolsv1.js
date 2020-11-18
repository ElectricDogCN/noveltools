//判断访问终端
var browser = {
    versions: function () {
        var u = navigator.userAgent,
            app = navigator.appVersion;
        return {
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android终端
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
            qq: u.match(/\sQQ/i) == " qq" //是否QQ
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
$(document).ready(function () {
    if (browser.versions.ios || browser.versions.iPad || browser.versions.iPhone) {
        var date_now = new Date();
        var preDate = new Date(date_now.getTime() - 24 * 60 * 60 * 1000 * 15);
        //任何需要执行的js特效
        $("#fkios").html('<input type="date" id="date" readonly="readonly" max="' + getDateStr(date_now) + '" min="' +
            getDateStr(preDate) + '" value="' +
            getDateStr(date_now) + '" style="width: 100px;"> &nbsp;')
        $("#date").blur(function () {
            if (str4Date($(this).val()).getTime() <= date_now.getTime() && str4Date($(this).val()).getTime() > preDate.getTime()) {
                return
            }
            alert("请选择" + getDateStr(preDate) + "至" + getDateStr(date_now) + "之间的日期！")
            $(this).val(getDateStr(date_now))
            $(this).trigger("blur")
            $(this).trigger("dblclick")
        })
    } else {
        laydate.render({
            elem: '#date',
            min: -15,
            max: 0,
            value: new Date(),
            showBottom: false
        });
    }
});


function str4Date(dateStr) {
    if (dateStr) {
        var date = new Date(dateStr)
        // var date = new Date(dateStr.replace(/-/, "/"))
        return date;
    }
}

function getDateStr(date) {
    var year = date.getFullYear();
    //得到当前月份
    //注：
    //  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
    //  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
    //得到当前日子（多少号）
    var date = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    //设置input标签的max属性
    return year + "-" + month + "-" + date
}

function showdiv() {
    document.getElementById("bg").style.display = "block";
    document.getElementById("show").style.display = "block";
    $("#info").hide()
}

function hidediv() {
    document.getElementById("bg").style.display = 'none';
    document.getElementById("show").style.display = 'none';
}


function queryData() {
    if ($("#usernameInput").val() == "") {
        alert("角色名不能为空！")
        return
    }
    showdiv()
    $.get("/300hero/" + $("#usernameInput").val() + "?date=" + $("#date").val(), function (data) {
        hidediv()
        var pdata = $.parseJSON(data)
        if (pdata.code != 0) {
            alert(pdata.msg)
            return
        }
        $("#info").show()
        pdata = pdata.data
        $("#username").text(pdata.username)
        $("#queryTime").text(pdata.queryTime)
        $("#queryDate").text(pdata.queryDate)
        $("#total").text(pdata.total)
        $("#rewardMoney").text(pdata.rewardMoney)
        $("#exp").text(pdata.exp)
        $("#assistTotal").text(pdata.assistTotal)
        $("#killTotal").text(pdata.killTotal)
        $("#deathTotal").text(pdata.deathTotal)
        $("#towerDestroy").text(pdata.towerDestroy)
        $("#killUnitTotal").text(pdata.killUnitTotal)
        $("#jjcWin").text(pdata.jjcWin)
        $("#jjcLose").text(pdata.jjcLose)
        $("#jjcRunaway").text(pdata.jjcRunaway)
        $("#jjcTotal").text(pdata.jjcTotal)
        $("#jjcKillTotal").text(pdata.jjcKillTotal)
        $("#jjcDeathTotal").text(pdata.jjcDeathTotal)
        $("#jjcAssistTotal").text(pdata.jjcAssistTotal)
        $("#jjcTowerDestroy").text(pdata.jjcTowerDestroy)
        $("#jjcKillUnitTotal").text(pdata.jjcKillUnitTotal)
        $("#jjcRewardMoney").text(pdata.jjcRewardMoney)
        $("#jjcRewardExp").text(pdata.jjcRewardExp)
        $("#jjcLastMatchTime").text(pdata.jjcLastMatchTime)
        $("#zcWin").text(pdata.zcWin)
        $("#zcLose").text(pdata.zcLose)
        $("#zcRunaway").text(pdata.zcRunaway)
        $("#zcTotal").text(pdata.zcTotal)
        $("#zcKillTotal").text(pdata.zcKillTotal)
        $("#zcDeathTotal").text(pdata.zcDeathTotal)
        $("#zcAssistTotal").text(pdata.zcAssistTotal)
        $("#zcTowerDestroy").text(pdata.zcTowerDestroy)
        $("#zcKillUnitTotal").text(pdata.zcKillUnitTotal)
        $("#zcRewardMoney").text(pdata.zcRewardMoney)
        $("#zcRewardExp").text(pdata.zcRewardExp)
        $("#zcLastMatchTime").text(pdata.zcLastMatchTime)
    })
}
