function submitForm(formid, url) {
	$("#"+formid).prop("action", url);
	$("#"+formid).prop("method", "post");
	$("#"+formid).get(0).submit();
}