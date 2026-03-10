Ext.define('ProjSistemaOs.store.Cliente', {
    extend: 'Ext.data.Store',
    alias: 'store.cliente-listagem-store',
    model: 'ProjSistemaOs.model.Cliente',

    remoteFilter: true,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'http://localhost:8080/api/cliente/listar',
        actionMethods: {
            read: 'GET'
        },
        reader: {
            type: 'json',
            rootProperty: 'clientes'
        }
    },
    listeners: {
        beforeLoad: function(store, operation) {
            var filtros = store.getFilters().items, arrayFiltro = [];
            for (var f of filtros) {
                if (f.getProperty() == "statusCliente") {
                    f.setValue(f.getValue() ? 1 : 0);
                }
            }
            console.log(store);
            arrayFiltro = filtros.map(f => ({
                propriedade: f.getProperty(),
                operador: f._operator,
                valor: f._value
            }));
            store.getProxy().setExtraParams({
                filtros: arrayFiltro
            });
        }
    }
})