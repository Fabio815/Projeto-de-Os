Ext.define('ProjSistemaOs.view.cliente.ClientesGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'clienteGrid',

	controller: {
        adicionarCliente: function(){
            var me = this, vw = me.getViewModel(),
                win = Ext.create('ProjSistemaOs.view.cliente.ClienteWindow');

            win.on('clienteSalvo', () => { //Aqui está escutando o envento que é disparadp quando salva o cliente.
                if (vw && !vw.destroyed && !vw.isDestroying) {
                    me.getView().getStore().load();
                }
            });
            win.show();
        },
        recarregarGrid: function () {
            var me = this, vw = me.getView();
            if (vw && !vw.destroyed && !vw.isDestroying) {
                me.getView().getStore().load();
            }
        }
    },

    enableColumnHide: false,
    requires: [
        'ProjSistemaOs.store.Cliente',
		'ProjSistemaOs.view.cliente.ClienteController',
		'ProjSistemaOs.view.cliente.ClienteWindow'
    ],

    title: 'Clientes',
    layout: 'fit',

    store: {
        type: 'cliente-listagem-store'
    },

	tbar: [{
		xtype: 'button',
		tooltip: 'Adicionar',
		iconCls: 'fa fa-plus',
		width: 40,
		height: 40,
		handler: 'adicionarCliente'
	}, {
        xtype: 'button',
        tooltip: 'Adicionar',
        iconCls: 'fa fa-sync',
        width: 40,
        height: 40,
        handler: 'recarregarGrid'
    }],
    columns: [{
        text: 'Id',
        dataIndex: 'id',
        width: 60,
        filter: {
            type: 'number',
            menuItems: ['eq']
        }
    }, {
        text: 'Nome',
        dataIndex: 'nome',
        width: 220,
        filter: 'string'
    }, {
        text: 'Telefone',
        dataIndex: 'telefone',
        flex: 1
    }, {
        text: 'Rua',
        dataIndex: 'rua',
        flex: 2
    }, {
        text: 'Bairro',
        dataIndex: 'bairro',
        flex: 1
    }, {
        text: 'Número',
        dataIndex: 'numero',
        width: 100
    }, {
        text: 'Complemento',
        dataIndex: 'complemento',
        flex: 2
    }, {
        xtype: 'checkcolumn',
        text: 'Ativo',
        dataIndex: 'statusCliente',
        width: 80,
        filter: {
            type: 'boolean',
            yes: 'true',
            no: 'false',
            default: true
        }
    }],
    plugins: {
        gridfilters: true
    },
    bbar: {
        xtype: 'pagingtoolbar',
        pageSize: 10,
        displayInfo: true,
        displayMsg: 'Página {0} - {1} de {2}',
        emptyMsg: 'Sem dados',
        store: {
            type: 'cliente-listagem-store'
        }
    }
});