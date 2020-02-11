$('#confirmarExclusao').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var codigo = button.data('idevento');
	alert(codigo);
});