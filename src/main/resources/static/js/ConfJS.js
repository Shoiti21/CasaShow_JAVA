$('#confirmarExclusaoEvento').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var codigo = button.data('idevento');
	var mensagem = button.data('nomeevento')
	
	var modal=$(this);
	var form=modal.find('form');
	var action=form.attr('action');
	if(!action.endsWith('/')){
		action+='/';
	}
	form.attr('action', action+codigo);
	
	modal.find('.modal-header h4').html('Você deseja excluir o evento <strong>'+mensagem+'</strong>?');
});

$('#confirmarExclusaoShow').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var codigo = button.data('idshow');
	var mensagem = button.data('nomeshow')
	
	var modal=$(this);
	var form=modal.find('form');
	var action=form.attr('action');
	if(!action.endsWith('/')){
		action+='/';
	}
	form.attr('action', action+codigo);
	
	modal.find('.modal-header h4').html('Você deseja excluir a Casa de Show <strong>'+mensagem+'</strong>?');
});
$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal:',',thousands:'.', allowZero:true});
});