# eHotel Buffet
## Project Description

In response to the challenging conditions within the hospitality industry, the eHotel initiative has been launched with the primary objective of automating and streamlining various aspects of hotel operations. The project's inaugural phase centers on the development of a buffet simulator, geared towards refining and optimizing the logistics of buffet management. This innovative buffet management module is designed to seamlessly integrate with the guest reservation system, sourcing data on current and future guests. Its mission is twofold: to minimize guest dissatisfaction by ensuring that buffet plates remain consistently well-stocked and to reduce food waste. These dual objectives present a unique challenge, given the unpredictable nature of guest demand, but the module capitalizes on all available data to strike a delicate balance.

## Technologies used: 
- **JUnit 5**: used for writing various test cases
- **Mockito**: used for creating mock objects

## Challanges

During development, we faced the following challenges:

- Creating a good and well functioning algorithm for a breakfast cycle
- Organising the functionality in different classes
- Generating and giving each guest a random check-in and check-out date based on a start date and end date

## Setup

Prior to starting the application, several rules are established within the breakfast algorithm:

- A season spans between a minimum of 3 days and a maximum of 1 month.
- Each guest can stay for a duration ranging from a minimum of 3 days to a maximum of 7 days.
- Following 3 cycles, any meal labeled as SHORT is considered expired and discarded.
- Each guest exclusively consumes the freshest meal available at the buffet.
- Upon breakfast completion, all meals labeled as SHORT and MEDIUM are disposed of

The application is run from the "EHotelBuffetApplication" file which contains the main class within it

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/d6bac2e4-078d-415f-977e-535b84b780db)

After running the application, we will receive a welcome message followed by a prompt requesting input for a start date

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/7bad7a23-bc06-47df-b0df-4c3c5995436a)

If the provided start date is in the correct format, we will then prompt for an end date

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/8669f894-f872-45e2-ba06-0dc9130bd85b)

After selecting the start and end dates, we will choose a date within this range to initiate the breakfast cycle

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/8ca4d1b5-7ef9-4c2c-ac83-9dabf83fd7a9)

## How to use

After the setup we will be getting into the terminal info about each cycle of the breakfast, as well as finding out at the end of the 8th cycle, the amount of happy guests, unhappy guests and the amount of money we lost during that day

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/019450b7-2420-408e-8954-28554ae365ac)

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/18958817-8fae-4d32-be5d-71e35d63e24e)

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/7fde653f-5501-406c-bce4-e4c39991c8b5)

![image](https://github.com/T-Andrei-C/eHotel-Buffet/assets/115529065/5b271a2e-831a-4054-badf-2b8c5da77cba)
