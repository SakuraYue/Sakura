function valiDate () {
	var flag = false;
	var count = 0;
	
	// 地区必选选择
	var v_area = document.getElementById("areaInfo").value;
	if (v_area == -1) {
		document.getElementById("areaTip").innerHTML = "<font color = 'red'>地区未选择</font>";
	} else {
		document.getElementById("areaTip").innerHTML = "";
		count++;
	}
	
	// 年代必选选择
	var v_movieTime = document.getElementById("movieTimeInfo").value;
	if (v_movieTime == -1) {
		document.getElementById("movieTimeTip").innerHTML = "<font color = 'red'>年代未选择</font>";
	} else {
		document.getElementById("movieTimeTip").innerHTML = "";
		count++;
	}
	
	// 类型必选选择
	var v_movieType = document.getElementById("movieTypeInfo").value;
	if (v_movieType == -1) {
		document.getElementById("movieTypeTip").innerHTML = "<font color = 'red'>类型未选择</font>";
	} else {
		document.getElementById("movieTypeTip").innerHTML = "";
		count++;
	}
	
	if (count == 3) {
		flag = true;
	}
	return flag;
}