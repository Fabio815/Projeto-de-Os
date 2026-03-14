Ext.define('ProjSistemaOs.view.cliente.ClienteController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.cliente-controller',

	requires: ['ProjSistemaOs.utils.Avisos'],

	adicionarCliente: function(){
		var janelaAdicionarCliente = Ext.create('ProjSistemaOs.view.cliente.ClienteWindow');
		janelaAdicionarCliente.show();
	},

	fecharJanela: function() {
		var vw = this.getView();
		if (vw && !vw.destroyed && !vw.isDestroying) {
			vw.close();
		}
	},

	salvarCliente: function() {
		var vw = this.getView(),
			form = vw.down('form').getForm().getValues();

		Ext.Ajax.request({
			url: 'http://localhost:8080/api/cliente/cadastrar',
			method: 'POST',
			jsonData: form,
			callback: function(options, success, response) {
				if (vw && !vw.destroyed && !vw.isDestroying) {
					var r = Ext.decode(response.responseText, true);

					if (r && r.sucesso) {
						vw.fireEvent('clientesalvo');
						ProjSistemaOs.utils.Avisos.mensagemSucesso(r.mensagem);
						vw.destroy();
					} else if (r) {
						ProjSistemaOs.utils.Avisos.mensagemAviso(r.mensagem);
					} else {
						ProjSistemaOs.utils.Avisos.mostrarServidorIndisponivel();
					}
				}
			}
		});
	}
});