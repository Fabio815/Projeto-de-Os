Ext.define('ProjSistemaOs.store.Cliente', {
    extend: 'Ext.data.Store',
    alias: 'store.cliente',

    model: 'ProjSistemaOs.model.Cliente',

    data: [
        {
            id: 1,
            nome: 'João Silva',
            telefone: '(47) 99911-2233',
            rua: 'Rua das Palmeiras',
            bairro: 'Centro',
            numero: '123',
            complemento: 'Apto 101',
            statusCliente: true
        },
        {
            id: 2,
            nome: 'Maria Oliveira',
            telefone: '(47) 98822-3344',
            rua: 'Rua do Comércio',
            bairro: 'Vila Nova',
            numero: '456',
            complemento: '',
            statusCliente: true
        },
        {
            id: 3,
            nome: 'Carlos Souza',
            telefone: '(47) 97733-4455',
            rua: 'Av. Brasil',
            bairro: 'Centro',
            numero: '789',
            complemento: 'Sala 2',
            statusCliente: false
        },
        {
            id: 4,
            nome: 'Ana Pereira',
            telefone: '(47) 96644-5566',
            rua: 'Rua das Flores',
            bairro: 'Jardim América',
            numero: '321',
            complemento: '',
            statusCliente: true
        },
        {
            id: 5,
            nome: 'Bruno Costa',
            telefone: '(47) 95555-6677',
            rua: 'Rua São João',
            bairro: 'Itoupava Norte',
            numero: '654',
            complemento: 'Fundos',
            statusCliente: true
        },
        {
            id: 6,
            nome: 'Fernanda Lima',
            telefone: '(47) 94466-7788',
            rua: 'Rua XV de Novembro',
            bairro: 'Centro',
            numero: '987',
            complemento: '',
            statusCliente: false
        },
        {
            id: 7,
            nome: 'Ricardo Alves',
            telefone: '(47) 93377-8899',
            rua: 'Rua Bahia',
            bairro: 'Garcia',
            numero: '159',
            complemento: 'Casa',
            statusCliente: true
        },
        {
            id: 8,
            nome: 'Juliana Martins',
            telefone: '(47) 92288-9900',
            rua: 'Rua Santa Catarina',
            bairro: 'Velha',
            numero: '753',
            complemento: 'Bloco B',
            statusCliente: true
        },
        {
            id: 9,
            nome: 'Paulo Henrique',
            telefone: '(47) 91199-0011',
            rua: 'Rua Amazonas',
            bairro: 'Escola Agrícola',
            numero: '852',
            complemento: '',
            statusCliente: false
        },
        {
            id: 10,
            nome: 'Camila Rocha',
            telefone: '(47) 90000-1122',
            rua: 'Rua Paraná',
            bairro: 'Fortaleza',
            numero: '147',
            complemento: 'Apto 202',
            statusCliente: true
        }
    ]
});