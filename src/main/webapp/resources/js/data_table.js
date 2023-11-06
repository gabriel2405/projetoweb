$(document).ready(function () {
    $('#datatable').DataTable({
        "language": {
            "url": "../resources/vendor/data-table/json/pt-BR.json"
        },
        "order": [[ 0, "desc" ]]
	});
	$('[data-toggle="tooltip"]').tooltip()
});