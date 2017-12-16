#Пример простого добавления scheduled-компоненты

##Условия

Spring boot приложение

##Задача

Выполнить в определённое время какие-нибудь действия.
Действия реализуются компонентом Spring.
Предложить шаблон проектирования. 

##Возможное решение

**Идея**
 
Регистрация действия и непосредственное действие реализуются в одном классе. 

Класс ассоциируем со spring profile. 

Указывая в настройках активные profiles можно выборочно активировать работу по расписанию соответствующих компонент.

```java

@Component
// связываем работу с профилем
@Profile(value = {"profile-job-1"})
// работаем если есть параметр scheduling.on: true
@ConditionalOnProperty(prefix = "scheduling.", name = "on")
public class JobOne implements Runnable, SchedulingConfigurer {

    private static Logger log = Logger.getLogger(JobOne.class.getName());

    // cron расписание
    @Value(value = "${scheduling.jobs.cron_1:0/3 * * * * *}")
    private String cron;

    //...

    @Override
    public void run() {
        log.info("JOBS_1");
        //...
    }

    /**
    * Самостоятельная регистрация в ScheduledTaskRegistrar
    */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        log.info("Init: " + cron);
        // самостоятельное добавление действия по расписанию в планировщик
        taskRegistrar.addCronTask(this, cron);
    }

}

```

Примеры файлов:

- **JobOne.java** (связан с профилем profile-job-1)
- **JobTwo.java** (связан с профилем profile-job-2)

В настройках **app.yml**

задавая параметр

```
spring.profiles.active: profile-job-1, profile-job-2
```

можно селективно выбирать задачи для выполнения по расписанию.

Параметр:

```
vpvexamples.schedulers.scheduling.threadpooltasks_cheduler_size: 1 
```
 задачёт количество потоков для выполнения задач планировщика.
 
 ##Запуск примера
 
 gradle assemble
  
 java -jar ./schedulers-1.0-SNAPSHOT.jar --spring.config.location=file:./app.yml
 
 