# ADA LocateCar - Locadora de Veículos

Esta é uma aplicação de gerenciamento de aluguel de veículos, desenvolvida com base nos conceitos apresentados em aula. Ela permite realizar as seguintes operações:

## Itens Obrigatórios

1. **Cadastro de Veículos**: É possível cadastrar novos veículos, usando a placa como identificador de unicidade.
2. **Alteração de Veículo Cadastrado**: É possível modificar informações de um veículo já cadastrado.
3. **Busca de Veículo por Parte do Nome**: É possível buscar um veículo com base em parte do nome.
4. **Cadastro de Cliente (Pessoa Física e Jurídica)**: Permite cadastrar clientes, usando CPF (Pessoa Física) e CNPJ (Pessoa Jurídica) como identificadores de unicidade.
5. **Alteração de Cliente (Pessoa Física e Jurídica)**: É possível modificar informações de clientes já cadastrados.
6. **Aluguel de Veículo para Pessoa Física e Jurídica**: Permite alugar um veículo para clientes, registrando o local, data e horário.
7. **Devolução de Veículo por Pessoa Física e Jurídica**: Registra a devolução de um veículo, incluindo o local, data e horário.

## Regras de Negócio

### RN1: Veículos Não Repetidos

- Os veículos não podem ser repetidos, sendo a placa utilizada como identificador de unicidade.

### RN2: Tipos de Veículos

- Os tipos de veículos considerados são: PEQUENO, MÉDIO e SUV.

### RN3: Registro de Aluguéis e Devoluções

- Os registros de aluguéis e devoluções incluem o local, data e horário.

### RN4: Cálculo de Diárias

- Aluguéis em horas quebradas são considerados diárias completas.
- Exemplo: Uma devolução de um veículo alugado em 25 de janeiro às 15h30 será cobrada uma (1) diária até 26 de janeiro às 15h30. A partir desse horário, serão cobradas duas (2) diárias e assim por diante.

### RN5: Veículos Alugados Não Disponíveis

- Os veículos que estiverem alugados não podem estar disponíveis para novos aluguéis.

### RN6: Clientes Não Duplicados

- Clientes não podem ser duplicados, usando CPF (Pessoa Física) e CNPJ (Pessoa Jurídica) como identificadores de unicidade.

### RN7: Regras de Devolução

- Cliente Pessoa Física com mais de 5 diárias terá direito a 5% de desconto.
- Cliente Pessoa Jurídica com mais de 3 diárias terá direito a 10% de desconto.

### Valores Base da Diária por Tipo de Veículo

- **PEQUENO**: R$ 100,00 por dia.
- **MÉDIO**: R$ 150,00 por dia.
- **SUV**: R$ 200,00 por dia.

Esta aplicação visa facilitar o gerenciamento de aluguéis de veículos, seguindo as regras de negócio estabelecidas. Você pode adaptar e expandir esta aplicação conforme necessário para atender aos requisitos específicos do seu projeto.
