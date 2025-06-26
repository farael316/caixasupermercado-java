# 🛒 Caixa de Supermercado

Um sistema completo de caixa de supermercado desenvolvido em Java com interface gráfica Swing, simulando um ambiente real de vendas.

## ✨ Funcionalidades

- **📦 Gestão de Estoque**: 20 produtos comuns pré-cadastrados
- **🛍️ Carrinho de Compras**: Adicionar, remover e gerenciar itens
- **💰 Cálculo Automático**: Subtotal e total da compra
- **📋 Recibo de Compra**: Geração automática de recibos
- **⌨️ Atalhos de Teclado**: Pressione Enter para adicionar produtos
- **📊 Interface Intuitiva**: Design moderno e fácil de usar

## 🚀 Como Executar

### Opção 1: Arquivo JAR (Recomendado)
```bash
java -jar CaixaMercado.jar
```
Ou simplesmente dê **duplo clique** no arquivo `CaixaMercado.jar`

### Opção 2: Compilar e Executar
```bash
# Compilar
javac -d bin src/main/java/com/supermercado/model/*.java src/main/java/com/supermercado/view/CaixaMercadoGUI.java

# Executar
java -cp bin com.supermercado.view.CaixaMercadoGUI
```

## 📋 Produtos Disponíveis

| Código | Produto | Preço | Estoque |
|--------|---------|-------|---------|
| 001 | Arroz Integral | R$ 8,50 | 50 |
| 002 | Feijão Preto | R$ 6,80 | 40 |
| 003 | Macarrão Espaguete | R$ 3,20 | 60 |
| 004 | Óleo de Soja | R$ 7,90 | 30 |
| 005 | Leite Integral | R$ 4,50 | 80 |
| 006 | Pão de Forma | R$ 5,20 | 25 |
| 007 | Queijo Mussarela | R$ 12,80 | 35 |
| 008 | Presunto | R$ 8,90 | 30 |
| 009 | Banana | R$ 4,80 | 100 |
| 010 | Maçã | R$ 6,50 | 70 |
| 011 | Tomate | R$ 5,90 | 45 |
| 012 | Cebola | R$ 3,40 | 60 |
| 013 | Batata | R$ 4,20 | 80 |
| 014 | Coca-Cola 2L | R$ 8,50 | 40 |
| 015 | Suco de Laranja | R$ 6,80 | 35 |
| 016 | Sabão em Pó | R$ 12,50 | 25 |
| 017 | Papel Higiênico | R$ 8,90 | 30 |
| 018 | Shampoo | R$ 15,80 | 20 |
| 019 | Escova de Dentes | R$ 4,50 | 40 |
| 020 | Pasta de Dente | R$ 3,90 | 50 |

## 🎯 Como Usar

1. **Adicionar Produto**:
   - Digite o código do produto no campo "Código do Produto"
   - Digite a quantidade desejada
   - Pressione **Enter** ou clique em "Adicionar ao Carrinho"

2. **Visualizar Carrinho**:
   - Os produtos aparecem na tabela central
   - Subtotal e total são calculados automaticamente

3. **Finalizar Compra**:
   - Clique em "Finalizar Compra"
   - Visualize o recibo gerado
   - O estoque é atualizado automaticamente

4. **Limpar Carrinho**:
   - Clique em "Limpar Carrinho" para começar uma nova compra

## 🏗️ Estrutura do Projeto

```
CaixaMercado/
├── src/main/java/com/supermercado/
│   ├── model/
│   │   ├── Produto.java          # Classe que representa um produto
│   │   ├── Estoque.java          # Gerencia o estoque de produtos
│   │   ├── ItemCompra.java       # Representa um item no carrinho
│   │   └── Carrinho.java         # Gerencia o carrinho de compras
│   └── view/
│       └── CaixaMercadoGUI.java  # Interface gráfica principal
├── bin/                          # Arquivos compilados (.class)
├── manifest.txt                  # Manifesto para o arquivo JAR
├── CaixaMercado.jar             # Arquivo executável
└── README.md                     # Este arquivo
```

## 🛠️ Tecnologias Utilizadas

- **Java 8+**: Linguagem de programação
- **Swing**: Interface gráfica
- **MVC Pattern**: Separação entre Modelo, Visão e Controle

## 📝 Recursos Técnicos

- **Gestão de Estoque**: Controle automático de quantidade disponível
- **Validações**: Verificação de produtos existentes e estoque suficiente
- **Interface Responsiva**: Design adaptável e intuitivo
- **Recibos Formatados**: Saída profissional para o cliente

## 🎨 Interface

A interface possui:
- **Painel Superior**: Entrada de produtos
- **Tabela Central**: Carrinho de compras
- **Painel Inferior**: Total e finalização
- **Painel Lateral**: Lista de produtos disponíveis

## 🔧 Requisitos

- **Java Runtime Environment (JRE) 8** ou superior
- **Java Development Kit (JDK)** para compilação (opcional)

---

**Desenvolvido com ❤️ em Java**

*Um sistema completo de caixa de supermercado para simular vendas reais!* 