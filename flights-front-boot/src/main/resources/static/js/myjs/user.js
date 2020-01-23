function valiDate () {
	var flag = false;
	var count = 0;
	
	// 用户名不能为空，用户名只能是3-9位的数字或者字母
	var v_userName = document.getElementById("userName").value;
	var v_userNameTip = document.getElementById("userNameTip").innerHTML;
	if (v_userName.length == 0) {
		document.getElementById("userNameTip").innerHTML = "<font color = 'red'>用户名为空</font>";
	} else if (!/^[0-9a-zA-Z]{3,9}$/.test(v_userName)) {
		document.getElementById("userNameTip").innerHTML = "<font color = 'red'>用户名只能是3-6为的数字或者字母</font>";
	} else if (v_userNameTip == '<font color="red">用户名已存在</font>') {
		document.getElementById("userNameTip").innerHTML = "<font color = 'red'>用户已存在</font>";
	} else {
		document.getElementById("userNameTip").innerHTML = "";
		count++;
	}
	
	// 真实姓名不能为空
	var v_userRealName = document.getElementById("userRealName").value;
	if (v_userRealName.length == 0) {
		document.getElementById("userRealNameTip").innerHTML = "<font color = 'red'>真实姓名为空</font>";
	} else {
		document.getElementById("userRealNameTip").innerHTML = "";
		count++;
	}
	
	// 密码不能为空，密码只能是6-12位的数字，字母或者下划线
	var v_userPwd = document.getElementById("userPwd").value;
	if (v_userPwd.length == 0) {
		document.getElementById("userPwdTip").innerHTML = "<font color = 'red'>密码为空</font>";
	} else if (!/^\w{6,12}$/.test(v_userPwd)) {
		document.getElementById("userPwdTip").innerHTML = "<font color = 'red'>密码只能是6-12位的数字，字母或者下划线</font>";
	} else {
		document.getElementById("userPwdTip").innerHTML = "";
		count++;
	}
	
	// 确认密码框必须和第一次密码一致
	var v_enterPwd = document.getElementById("enterPwd").value;
	if (v_enterPwd.length == 0) {
		document.getElementById("enterPwdTip").innerHTML = "<font color = 'red'>确认密码为空</font>";
	} else if (v_enterPwd != v_userPwd) {
		document.getElementById("enterPwdTip").innerHTML = "<font color = 'red'>与上面密码不一致</font>";
	} else {
		document.getElementById("enterPwdTip").innerHTML = "";
		count++;
	}
	
	//必须选择一项
	var v_sexArr = document.getElementsByName("user.sex");
	var tempFlag = 0;
	for (var i = 0; i < v_sexArr.length; i++) {
		if (v_sexArr[i].checked) {
			tempFlag = 1;
			break;
		}
	}
	if (tempFlag == 0) {
		document.getElementById("sexTip").innerHTML = "<font color = 'red'>请选择一项</font>";
	} else {
		document.getElementById("sexTip").innerHTML = "";
		count++;
	}
	
	// 手机号不能为空，手机号只能是11位的数字
	var v_phone = document.getElementById("phone").value;
	if (v_phone.length == 0) {
		document.getElementById("phoneTip").innerHTML = "<font color = 'red'>手机号为空</font>";
	} else if (!/^13\d{9}$|^15\d{9}|^18\d{9}|^17\d{9}$/.test(v_phone)) {
		document.getElementById("phoneTip").innerHTML = "<font color = 'red'>手机号只能是11位的数字</font>";
	} else {
		document.getElementById("phoneTip").innerHTML = "";
		count++;
	}
	
	// 邮箱不能为空，邮箱以字母数字开头（1~2位）,中间为数字（3位）、字母/下划线(3位)@数字（3~4位）.com或者.cn或者.com.cn
	var v_email = document.getElementById("email").value;
	if (v_email.length == 0) {
		document.getElementById("emailTip").innerHTML = "<font color = 'red'>邮箱为空</font>";
	} else if (!/^[a-zA-Z0-9]{1,2}\d{3}[a-zA-Z_]{3}@\d{3,4}\.(com|cn|com\.cn)$/.test(v_email)) {
		document.getElementById("emailTip").innerHTML = "<font color = 'red'>邮箱以字母数字开头（1~2位）,中间为数字（3位）、字母/下划线(3位)@数字（3~4位）.com或者.cn或者.com.cn</font>";
	} else {
		document.getElementById("emailTip").innerHTML = "";
		count++;
	}
	
	if (count == 7) {
		flag = true;
	}
	return flag;
}