# Projeto Servidor Redes

Este projeto foi desenvolvido para configurar um servidor utilizando o **Ubuntu Server** em uma máquina virtual no VirtualBox. Ele inclui uma aplicação Java Web, configuração de um banco de dados MySQL, instalação do Moodle e outras ferramentas essenciais para um ambiente completo de servidor.

## Configuração Inicial

1. Baixar a ISO do **Ubuntu Server** no [site oficial](https://ubuntu.com/download/server).
2. Criar uma máquina virtual no **VirtualBox** com as seguintes configurações:
   - Nome: `projetofinal`
   - Memória RAM: mínimo recomendado: 4GB
   - Disco rígido: recomendado: 50GB ou mais
   - Processador: 2 CPUs
   - Rede: Adaptador com Ponte (Bridge Adapter)

## Ferramentas Instaladas

- **SSH**: Para acesso remoto ao servidor.
- **MySQL**: Configurado com usuário `root` e senha `Root@1234`.
- **Apache com PHP**: Para hospedar aplicações web.
- **Tomcat**: Configurado para deploy da aplicação Java Web.
- **Moodle**: Instalado e configurado para acesso via navegador.

## Aplicação Java Web

- Desenvolvida no **NetBeans** e implantada no **Tomcat 9**.
- O build gerado (`MeuProjetoWeb.war`) é movido para o diretório `/opt/tomcat/webapps` para deploy.
- Banco de dados configurado no MySQL para armazenar informações.

## Projeto no VirtualBox

O projeto completo, incluindo a máquina virtual exportada e a aplicação, está disponível no repositório do GitHub:

- **Repositório GitHub**: [https://github.com/ThalesTonon/ProjetoServidorRedes](https://github.com/ThalesTonon/ProjetoServidorRedes)
- Arquivo de importação da máquina virtual: `MaquinaVirtualProjetoServer.ova`
- Credenciais:
  - **Usuário do servidor**: `user`
  - **Senha do servidor**: `root`
  - **Usuário do MySQL**: `root`
  - **Senha do MySQL**: `Root@1234`

## Acesso ao Moodle

- URL de exemplo: `http://<ip_do_servidor>/moodle`

## Acesso à Aplicação Web

- URL de exemplo: `http://<ip_do_servidor>:8080/MeuProjetoWeb/`

## Instruções Adicionais

Siga as instruções detalhadas no [repositório do projeto](https://github.com/ThalesTonon/ProjetoServidorRedes) para importar a máquina virtual e configurar o ambiente.

---

Este projeto foi desenvolvido por **Matheus Gabriel**, **Nicholas**, **Suzana**, **Thales** e **Webert**.
