Ext.define('ProjSistemaOs.view.cliente.ClientesGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'clienteGrid',
	controller: 'cliente-controller',
    requires: [
        'ProjSistemaOs.store.Cliente',
		'ProjSistemaOs.view.cliente.ClienteController',
		'ProjSistemaOs.view.cliente.ClienteWindow'
    ],

    title: 'Clientes',
    layout: 'fit',

    store: {
        type: 'cliente'
    },

	tbar: [{
		xtype: 'button',
		tooltip: 'Adicionar',
		iconCls: 'fa fa-plus',
		width: 40,
		height: 40,
		handler: 'adicionarCliente'
	}],
    columns: [
        { text: 'Id', dataIndex: 'id', width: 60 },
        { text: 'Nome', dataIndex: 'nome', width: 220 },
        { text: 'Telefone', dataIndex: 'telefone', flex: 1 },
        { text: 'Rua', dataIndex: 'rua', flex: 2 },
        { text: 'Bairro', dataIndex: 'bairro', flex: 1 },
        { text: 'Número', dataIndex: 'numero', width: 100 },
        { text: 'Complemento', dataIndex: 'complemento', flex: 2 },
        {
            xtype: 'checkcolumn',
            text: 'Ativo',
            dataIndex: 'statusCliente',
            width: 80
        }
    ]
});