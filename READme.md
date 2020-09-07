Below you can find instructions on how to use this web application:

1. Open VisitationAppTable.sql script file in your MySQL database and run the scripts in order to create visitations schema and 
reservations/specialists table with mocked data.

2. Open the ReservationApp project directory in your IDE (e.g. IntelliJ) and run the project locally. The project is set to run on port 8080.

3. Access the application service department screen by link http://localhost:8080/

![alt text](https://ibb.co/PhLRvHR)

<h3>How to use customer's department</h3>

4. In order to make a reservation, choose the desired specialist, visit date and press save. After successful reservation, you will be
redirected to a page where you can see your reservation code, time left until your visit and how much visitors are registered before you in the waiting line.

![alt text](https://ibb.co/WzvdXxY)

5. Customer, who have an existing reservation, may check the reservation by entering his reservation code
and pressing check button. Customer will be redirected to the customer service department page where he can check reservation information and cancel the reservation.

<h3>How to use specialist's department</h3>

6. In order to check current and upcoming reservations of the specialist, you must choose the corresponding specialist and press
check visits. You will be redirected to a login page to authenticate yourself as a user. Type <b>user</b> as a username and <b>user</b> as password
to login.

![alt text](https://ibb.co/3Wp685V)

7. After successful login, you will be redirected to specialist department page. Specialist may choose to start visit of customer by pressing start visit button
or cancel visit by pressing cancel visit link.

![alt text](https://ibb.co/njHKkcR)

8. After pressing the start visit button, the visit will be posted as a current visit and the possibility to end visit will appear. At the same time only 1 current visit is allowed,
therefore it is not possible to start the new visit until the current visit is ended. The specialist department page is set to update information every 5 seconds.

![alt text](https://ibb.co/wM9S5Nv)

