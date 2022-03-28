#language: pt
Funcionalidade: Remover evento

  Cenário: apagar evento
    Dado que possuo o ID do usuário e o ID do evento
    Quando eu tentar remover um evento
      E for admin ou dono do grupo do evento
    Então o evento deve ser apagado

  Cenário: grupo não encontrado para o evento
    Dado que possuo o ID do usuário e o ID do evento
    Quando eu tentar remover um evento
      E não encontrar o grupo do evento
    Então um erro deve ser retornado

  Cenário: usuário não pertence ao grupo
    Dado que possuo o ID do usuário e o ID do evento
    Quando eu tentar remover um evento
      E o usuário não pertencer ao grupo do evento
    Então um erro deve ser retornado

  Cenário: usuário não tem permissão
    Dado que possuo o ID do usuário e o ID do evento
    Quando eu tentar remover um evento
      E o usuário não for admin ou dono do grupo
    Então um erro deve ser retornado