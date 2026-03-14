Ext.define('ProjSistemaOs.utils.Avisos', {
    singleton: true,

    mensagemSucesso: function(msg) {
        Ext.toast({
            html: msg || 'Operação realizada com sucesso',
            title: 'Sucesso',
            align: 't',
            width: 250
        });
    },

    mensagemAviso: function(msg) {
        Ext.toast({
            html: msg || 'Atenção',
            title: 'Aviso',
            align: 't',
            width: 250
        });
    },

    mostrarServidorIndisponivel: function() {
        Ext.toast({
            html: 'Servidor indisponível ou erro de conexão.',
            title: 'Erro',
            align: 't',
            width: 250
        });
    }
});