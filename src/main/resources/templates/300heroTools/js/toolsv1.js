laydate.render({
	elem: '#date',
	min: -15,
	max: 0,
	value: new Date(),
	showBottom: false
});

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
	if($("#usernameInput").val()==""){
		alert("角色名不能为空！")
		return
	}
		showdiv()
	$.get("http://127.0.0.1:8080/300hero/" + $("#usernameInput").val() + "?date=" + $("#date").val(), function(data) {
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
		$("#jjcKillTotal").text(pdata.jjcKillTotal)
		$("#jjcDeathTotal").text(pdata.jjcDeathTotal)
		$("#jjcAssistTotal").text(pdata.jjcAssistTotal)
		$("#jjcTowerDestroy").text(pdata.jjcTowerDestroy)
		$("#jjcKillUnitTotal").text(pdata.jjcKillUnitTotal)
		$("#jjcRewardMoney").text(pdata.jjcRewardMoney)
		$("#jjcRewardExp").text(pdata.jjcRewardExp)
		$("#zcKillTotal").text(pdata.zcKillTotal)
		$("#zcDeathTotal").text(pdata.zcDeathTotal)
		$("#zcAssistTotal").text(pdata.zcAssistTotal)
		$("#zcTowerDestroy").text(pdata.zcTowerDestroy)
		$("#zcKillUnitTotal").text(pdata.zcKillUnitTotal)
		$("#zcRewardMoney").text(pdata.zcRewardMoney)
		$("#zcRewardExp").text(pdata.zcRewardExp)
		console.log(pdata.username)
	})
}


