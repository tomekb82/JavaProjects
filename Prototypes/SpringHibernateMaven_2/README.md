

konfiguracja pgadmin:

username: postgres
passwd: password


nalezy stworzyc baze danych:
- baza: mkyong
- tabela: stock
- kolumny: 
stock_id(serial, not null, primary key)
stock_code(character varying, not null)
stock_name(character varying, not null)

