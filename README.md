<h1>Bus Buddy</h1>
Ride in style, book a mile </br>

<hr></hr>

<p>Bus Buddy is a online bus ticket booking platform,
where travellers book their bus tickets with best 
offers.</p>

<hr></hr>

![flow](https://user-images.githubusercontent.com/112754559/221497490-f005bbce-1a64-4980-a9ce-1cd39e32be83.jpg)

<hr></hr>
<b>Customers Table:</b>
CustomerID, FirstName, LastName, DOB, City, State, Zipcode, Phone, Email, DateEntered, TotalBalance(₹)

CREATE TABLE Customers (
	customerID int PRIMARY KEY AUTO_INCREMENT,
	firstName varchar(15) NOT NULL,
	lastName varchar(15) NOT NULL,
	dob date NOT NULL,
	city varchar(20) NOT NULL,
	state varchar(20) NOT NULL,
	zipCode int(6) NOT NULL,
	phone varchar(10) UNIQUE NOT NULL,
	emailID varchar(50)  UNIQUE NOT NULL,
	Password varchar(15) NOT NULL,
	dateEntered date NOT NULL,
	totalBalance double NOT NULL
);

INSERT INTO Customers 
(firstName, lastName, dob, city, state, zipCode, phone, emailID, Password, dateEntered, totalBalance) 
VALUES 
('Prince', 'Kumar', '2004-07-22', 'Bokaro', 'Jharkhand', '811111', '9874566547', 'prince@mail.com', '123456', '2022-01-14', 100000000);

<hr></hr>


<b>Buses Table:</b>
BusID, BusName, BusType, TotalSeats, DeparturePoint, ArrivalPoint, DepartureTime, ArrivalTime, TicketPrice

CREATE TABLE Buses (
busID int PRIMARY KEY AUTO_INCREMENT,
busName varchar(25) NOT NULL,
busType varchar(10) NOT NULL,
totalSeat int NOT NULL,
departurePoint varchar(25) NOT NULL,
arrivalPoint varchar(25) NOT NULL,
departureTime time NOT NULL,
arrivalTime time NOT NULL,
ticketPrice double NOT NULL
);

<hr></hr>

<b>Bookings Table:</b>
BookingID, CustomerID, BusID, BookedDate, NoOfPassenger, TotalAmount

CREATE TABLE Bookings (
	bookingID int PRIMARY KEY AUTO_INCREMENT,
	customerID int NOT NULL,
	busID int NOT NULL,
bookingDate date NOT NULL,
	bookedDate date NOT NULL,
	noOfPassenger int NOT NULL,
	totalAmount double NOT NULL,
	status varchar(9) NOT NULL,
	FOREIGN KEY (customerID) REFERENCES Customers(customerID),
	FOREIGN KEY (busID) REFERENCES Buses(busID)
);

INSERT INTO Bookings (customerID , busID, bookingDate, bookedDate, noOfPassenger, totalAmount, status) Values (1, 1,”2023-02-20”, “2023-02-25”, 2, 5000, “Active”);
