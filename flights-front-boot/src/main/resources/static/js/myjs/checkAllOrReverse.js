function checkAll() {
	$("input[name='ids']:checkbox").prop("checked",$("#all").prop("checked"));
}
function reverser() {
	$("input[name='ids']:checkbox").each(function() {
		$(this).prop("checked",!$(this).prop("checked"));
	});
}