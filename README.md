# Projeto Piloto (Mestre dos Magos 3)

![estacio-logo.png](readme-images/estacio-logo.png)

**Participantes:**

Luan Augusto Vieira Bandeira - 202209181337

Felipe Andrade de Oliveira Barroso - 202208291597;

Gabriel Henrique dos Santos - 202208292411;

Analécia Mariana Oliveira dos Santos - 202208453872;

Caique Thomaz Stopiglia - 2022.08.29247-1;

Leandro Luiz Lottermann - 202208565042;

Manual da Aplicação e ordem de execução:

## 📃 Processos

<aside>
📃 POST “/processos” (cadastra um novo processo)

⚠️ Corpo requerido

```json
{
	"numProcesso": "002"
}
```

GET “/processos” (retorna todos os processos com paginação)

GET “/processos/[id]” (retorna as informações do processo especificado no id)

DELETE “/processos/[id]” (deleta o processo especificado no id)

</aside>

## 👨‍👩‍👦 Partes

<aside>
👨‍👩‍👦 POST “/partes/[numProcesso]” (cadastra uma parte de algum processo)

⚠️ Corpo requerido

```json
{
	"nome": "Leandro Lottermann", //obrigatório
	"documento": "123124124",
	"email": "estacio@email.com",
	"endereco": {
		"cep": "01001000", //se informado o endereço, não pode ser nulo
		"logradouro": "Praça da Sé",
		"complemento": "lado ímpar",
		"bairro": "Sé",
		"cidade": "São Paulo",
		"uf": "SP",
		"numero": "144" //se informado o endereço, não pode ser nulo
	}
}
```

Obs: no caso de só informar o CEP e número, o sistema preenche automaticamente o restante dos dados de endereço.

GET “/partes” (retorna todas as partes com paginação)

GET “/partes/[id]” (retorna as informações da parte especificado no id)

PUT “/partes/[id]” (atualiza as informações da parte especificado no id)

⚠️ Corpo requerido

```json
{
	"documento": "1213123124124",
	"email": "aprendapiano.leandro@gmail.com",
}
```

Obs.: O campo nome não pode ser alterado e nem o campo documento se já tiver sido cadastrado anteriormente na parte.

DELETE “/partes/[id]” (deleta a parte especificada no id)

</aside>

## 📨 Notificações

<aside>
📨 POST “/notificacoes” (cadastra uma nova notificação)

⚠️ Corpo requerido

```json
{
	"idParte": "1",
	"textoNotificacao": "primeiro texto",
	"concluida": false	
}
```

Sobre o campo “concluída”: se for true, ele automaticamente inicia o processo de envio salvando o endereço da parte no respectivo momento se tiver e impossibilitando qualquer tipo de nova alteração.

GET “/notificacoes” (retorna todas as notificações e seus respectivos processos)

GET “/notificacoes/[idNotificacao]” (retorna a notificação especificada)

PUT “/notificacoes/[idNotificacao]” (atualiza a notificação específica)

⚠️ Corpo requerido

```json
{
	"textoNotificacao": "primeiro texto",
	"concluida": true	
}
```

Nesse caso então é iniciado o processo de envio da seguinte forma: Se a parte vinculada possuir endereço, a notificação entrara para a fila de espera para formar um lote de 10 para envio, se não tentará pelo email da parte e numa última forma pela publicação no DJE.

DELETE “/notificacoes/[idNotificacao]” (deleta a notificação específica)

</aside>

## 🚀 E-Carta

<aside>
🚀 GET “/ecarta” (retorna um lote de no mínimo 10 notificações prontas para serem entregues pelo correio)

PUT “/ecarta” (usado pelo ecarta para enviar um lote de notificacoes para confirmar que serão entregues pelo sistema)

⚠️ Corpo requerido: envia todas as cartas recebidas anteriormente no endpoint acima.

PUT “/ecarta/[idNotificacao]” (usado para atualizar em qual parte da entrega está a Notificação)

⚠️ Corpo requerido

```json
{
	"statusNotificacao": "entre filiais"
}
```

</aside>

## 📰 DJE

<aside>
📰 GET “/dje” (retorna as notificações disponíveis para a publicação no DJE)

PUT “/dje/[idNotificacao]” (usada pelo dje para atualizar o status da notificação quando for publicada)

</aside>

# UML do Projeto:

Link: [https://drive.google.com/file/d/1gukfF9RZfFGx8OfqHHjiMMAzrVSMsK_H/view?usp=sharing](https://drive.google.com/file/d/1gukfF9RZfFGx8OfqHHjiMMAzrVSMsK_H/view?usp=sharing)

![DadosNotificacaoDetalhesNoEndereco.png](readme-images/DadosNotificacaoDetalhesNoEndereco.png)

# Banco de dados (SQL-Server):

![bancomm3.png](readme-images/bancomm3.png)