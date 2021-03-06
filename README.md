Система, проектируемая в ходе выполнения курсового проекта, является системой автоматизации работы банка, а именно, процессов, связанных с кредитованием: процесс получения кредита, реструктуризации его и процесс генерации специального предложения для клиентов банка.    

#Роли
 
 **Клиент.**     
1. Клиент подает заявку на получение кредита.   
2. Клиент подает заявку на реструктуризацию кредита.   
2. При получении положительного ответа, клиент может согласиться на сделку, если его устраивают предложенные условия (кредитный план) или отказаться.    
3. Клиенты получают специальные предложения кредитования от банка.   
4. Если клиента устраивает специальное предложение, он может согласиться и заключить договор, иначе проигнорировать.  
 
 **Финансист.**     
1. Финансист на основе анализа документов  принимает решение об одобрении или отказе заявления клиента.    
2. При одобрении финансист выбирает кредитный план для клиента.    
3. Финансист генерирует специальные предложения кредитования.      
  
 **Менеджер**.    
1. Менеджер принимает заявки от клиента.   
2. Менеджер отправляет клиентам решения финансиста.   
3. Менеджер заключает договоры.   
4. Менеджер отправляет клиентам специальные предложения. 
5. Менеджер отправляет финансистам заявки от клиентов. 

#Разработка вариантов использования
[Use Case diagram] (https://github.com/Nikitina-Ann/design/blob/master/UseCase.PNG)

#Подробное описание вариантов использования 
##Процесс получения кредита
1. Клиент подает заявку на получение кредита, предоставляя документы о доходах.
2. Менеджер принимает заявку.
3. Финансист анализирует документы: полученные данные о доходах клиента, кредитную историю.
4. Финансист одобряет получение кредита, предлагая определенный кредитный план.
5. Менеджер оглашает решение финансиста клиенту.
6. Клиент соглашается на предложенный кредитный план и заключает с менеджером договор на получение кредита.    

**Альтернатива:** Рейтинговая история       
3а. Если клиент уже обращался в этот банк, он имеет свой рейтинг. Поэтому на шаге 3 необходимо проверить рейтинг клиента. 

**Альтернатива:** Отказ финансиста      
4. На шаге 4 финансист отказывает клиенту в получении кредита.   
5. Менеджер оглашает решение финансиста клиенту.     
6. Отказ от сделки.      

**Альтернатива:** Отказ клиента   
6. На шаге 6 со стороны клиента поступает отказ от предложенных финансистом условий кредита (кредитного плана). Отказ от сделки.   
 
##Процесс реструктуризации кредита
1. Клиент подает заявку на реструктуризацию кредита, предоставляя документы о доходах и документы о причинах реструктуризации.
2. Менеджер принимает заявку.
3. Финансист анализирует документы: полученные данные о доходах клиента, кредитную историю, рейтинг клиента и документы о причинах реструктуризации.
4. Финансист одобряет реструктуризацию кредита, предлагая новый кредитный план.   
5. Менеджер оглашает решение финансиста клиенту.
6. Клиент соглашается на новый кредитный план и заключает с менеджером договор на реструктуризацию кредита.    

**Альтернатива:** Отказ финансиста   
4. На шаге 4 финансист отказывает клиенту в реструктуризации кредита.        
5. Менеджер оглашает решение финансиста клиенту.     
6. Отказ от сделки.    

**Альтернатива:** Отказ клиента    
6. На шаге 6 со стороны клиента поступает отказ от предложенных финансистом условий кредита (кредитного плана). Отказ от сделки. 

##Процесс генерации специального предложения   
1. Финансист, анализируя кредитную историю и рейтинг клиента, генерирует для него специальное предложение.   
2. Менеджер отправляет специальное предложение клиенту.
3. Клиент получает специальное предложение.   
4. Клиент принимает предложение и  заключает договор с менеджером.

**Альтернатива:** Игнорирование     
5. Клиент игнорирует специальное предложение, процесс завершается.

#Диаграмма классов 
[Диаграмма классов] (https://github.com/Nikitina-Ann/design/blob/master/ClassDiagr.png)

#Диаграмма полследовательностей   
[Получение кредита] (https://github.com/Nikitina-Ann/design/blob/master/SeqDiagr2.png)  
[Реструктуризация кредита] (https://github.com/Nikitina-Ann/design/blob/master/SeqDiagr1.png)   
[Специальное предложение] (https://github.com/Nikitina-Ann/design/blob/master/SeqDiagr3.png)   

#Проектирование слоя бизнесс-логики

В качестве архитектурного шаблона был выбран шаблон "Модель предметной области". В связи с этим, были выделены сущности предметной области и для каждой из сущностей было решено создать отдельный класс, описывающий данную сущность. Для сокрытия реализации, был применен паттерн фасад.
Реализация слоя бизнесс-логики

Для классов слоя бизнесс-логики был создан отдельный пакет (businessLogic).
Пакет содержит следующие классы    
* [AllBid](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/AllBid.java) - класс, описывающий заявки всех типов   
* [Bid](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/Bid.java) - класс, описывающий на получение кредита    
* [RestrBid](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/RestrBid.java) - класс, описывающий заявки на реструктуризацию кредита      
* [Client](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/Client.java) - класс, описывающий клиента   
* [Manager](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/Manager.java)- класс, описывающий менеджера
* [Financier](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/Financier.java)- класс, описывающий финансиста   
* [SpecialOffer](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/SpecialOffer.java) - класс, описывающий специальное предложение   
* [ResponceFinancier](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/ResponceFinancier.java) - класс, описывающий ответ финансиста   
* [Agreement](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/businessLogic/Agreement.java) - класс, описывающий договор    

Так же был создан отдельный пакет для фасада (facade), в котором содержатся классы:

* [BidFasade](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/fasade/BidFasade.java) - фасад для работы с заявками на получение кредтита      
* [RestrBidFasade](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/fasade/RestrBidFasade.java) -  фасад для работы с заявками на реструктуризацию кредита          
* [ClientFasade](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/fasade/ClientFasade.java) -  фасад для работы с клиентами     
* [ManagerFasade](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/fasade/ManagerFasade.java) -  фасад для работы с менеджерами    
* [FinancierFasade](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/fasade/FinancierFasade.java) -  фасад для работы с финансистами      
* [SpecialOfferFasade](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/fasade/SpecialOfferFasade.java) -  фасад для работы со специальнми предложениями       

#Тестирование

Для проверки работоспособности бизнесс-логики были написаны JUnit - тесты. Так как на этапе создания бизнесс-логики, слой хранения еще не был реализован, то был созданы репозитории, эмулирующие работу слоя хранения. Для этого был создан отдельный пакет repository, пакет включает следующие классы:     
* [BidRepo](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/repository/BidRepo.java)- репозиторий для работы с заявками на получение кредита      
* [RestrBidRepo](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/repository/RestrBidRepo.java)-   репозиторий  для работы с заявками на реструктуризацию кредита          
* [ClientRepo](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/repository/ClientRepo.java) -   репозиторий  для работы с клиентами     
* [ManagerRepo](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/repository/ManagerRepo.java) -   репозиторий  для работы с менеджерами    
* [FinancierRepo](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/repository/FinancierRepo.java) -   репозиторий  для работы с финансистами      
* [SpecialOfferRepo](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/repository/SpecialOfferRepo.java) -   репозиторий  для работы со специальнми предложениями       

Используя данные репозитории были написаны JUnit тесты, реализующие возможные варианты использования.

#Проектирование слоя хранения данных

Для работы со слоем хранения был выбран паттерн "Шлюз таблицы данных", ввиду удобства его использования в данной архитектуре. Для этого было решено создать отдельный пакет , в котором для каждой из сущностей будет создан отдельный шлюз.
#Реализация слоя хранения данных

В качестве СУБД было решено использовать MySQL в силу её удобства и бесплатности. Lля слоя хранения был создан отдельный пакет (services), в котором были созданы подпакеты (по аналогии с бизнесс-логикой). В состав пакета входят следующие классы:
* [BidService](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/BidService.java) - класс, отвечающий за обращение к таблице с завками на получение кредита    
* [RestrBidService] (https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/RestrBidService.java) - класс, отвечающий за обращение к таблице я заявками на реструктуризацию кредита      
* [ClientService] (https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/ClientService.java) - класс, отвечающий за обращение к таблице с клиентами   
* [ManagerService](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/ManagerService.java)  - класс, отвечающий за обращение к таблице с менеджерами
* [FinancierService](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/FinancierService.java)  - класс, отвечающий за обращение к таблице с финансистами   
* [SpecialOfferService] (https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/SpecialOfferService.java) - класс, отвечающий за обращение к таблице со специальными предложениями     
* [ResponceFinancierService](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/ResponceFinancierService.java)  - класс, отвечающий за обращение к таблице с ответами финансиста     
* [AgreementService](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/db/AgreementService.java)  -класс, отвечающий за обращение к таблице с договорами       

Так же были созданы соответствующие таблицы, для хранения сущностей.

#Проектирование слоя представления

Для слоя представления было решено выделить отдельный пакет (gui). Общение между слоем представления и слоем бизнесс-логики происходит через фасады, описанне ранее.    

#Проектирование интеграции со сторонними сервисами

В рамках работы было решено сделать 2 способа интеграции со сторонними сервисами:
1.   Для получение данных о системе извне. Для этого было решено сделать сервис, выдающий список всех менеджеров системы.
2.    Для подгрузки данных в систему. Для этого было решено создать текстовый файл, в котором, в формате json, будет хранится дополнительная информация о клиентах (должность на работе).

#Реализация слоя представления

Для классов, описывающих слой представления были создан отдельный пакет (gui). В пакете реализованы следующие классы:
*   [StartFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/StartFrame.java) - начально окно    
*   [ClientFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/ClientFrame.java) - окно клиента  
*   [FinancierFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/FinancierFrame.java) - окно финансиста   
*   [ManagerFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/ManagerFrame.java) - окно менеджера   
*   [RegiastrationFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/RegiastrationFrame.java) -окно регистрации   
*   [CreateNewBidFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/CreateNewBidFrame.java) - окно создания новой заявки на получение кредита (вызывается из окна клиента)   
*   [CreateSpecialOfferFrame](https://github.com/Nikitina-Ann/design/blob/master/Bank/src/main/java/com/mycompany/bank/gui/CreateSpecialOfferFrame.java)- окно создания нового специльного предложения (вызывается из окно финансиста)       

На рисунках ниже представлены скриншоты графического интерфейса.    
[Начальное окно] (https://github.com/Nikitina-Ann/design/blob/master/pngFrame/StartFrame.png)   
[Окно клиента] (https://github.com/Nikitina-Ann/design/blob/master/pngFrame/ClientFrame.png)    
[Окно финансиста] (https://github.com/Nikitina-Ann/design/blob/master/pngFrame/FinancierFrame.png)   
[Окно менеджера] (https://github.com/Nikitina-Ann/design/blob/master/pngFrame/ManagerFrame.png)   
[Окно регистрации] (https://github.com/Nikitina-Ann/design/blob/master/pngFrame/RegistrationFrame.png)     

#Реализация интеграции со сторонними сервисами

Для реализации слоя, отвечающего за выдачу информации о назначенном времени был создан отдельный класс HttpServer. Для передачи данных, использовался протокол HTTP. Данные передаются в формате json. В процессе работы сервер слушает порт 8080, и при установлении соединения, отправляет строку с данными. Ниже приведен пример получаемой строки:

[{"id":1,"name":"Свиридов Антон"},{"id":2,"name":"Березуцкий Сергей"}]

Как было сказанно выше, получение дополнительной информации было решено реализовать из текстового файла в формате json. Ниже приведен пример этого файла:    
[  
	{   
		"name" :"Иванов Василий",    
		"job" : "Строитель"    
	},   
	{   
		"name" :"Малышев Павел",   
		"job" : "Охранник"   
	}   
]    

Для чтение файла, поиск необходимой информации и выдачу информации отвечаeт класс:
ClientService - класс, осуществляющий разбор файла и выдачу соответствующий информации.



#Заключение

В рамках данного курса были изучены принципы разработки архитектуры программного обеспечения, а так же, следуя этим принципам, было разработано приложение. в приложении было создано три слоя:   

*  Слой бизнесс-логики  
*  Слой хранения данных   
*  Слой представления  

Расслоение добавляет гибкость приложению и позволяет изменять отдельный его слой без изменений остальных слоёв .
