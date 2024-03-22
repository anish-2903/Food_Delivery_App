#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure for menu items
struct MenuItem {
    int serial;
    int price;
    char name[40];
    struct MenuItem *next;
};

// Global variables
int serial = 1;
int total = 0;
char name1[50], address1[500], email1[25], phone1[15];
struct MenuItem *head = NULL, *temp = NULL;

// Function prototypes
void OwnerLogin();
void CustomerLogin();
void TakingInput();
void Create(char[], int);
void Display();
void Order();
void Menu();
void Choice();
void ByFood();
void ByHotel();
void Payment();
void Invoice();

int main() {
    printf("\033[1;31m");
    printf("\t\t\t|--------------------------------------------------------------------|\n");
    printf("\t\t\t|                WELCOME TO THE FOOD DELIVERY SYSTEM                 |\n");
    printf("\t\t\t|--------------------------------------------------------------------|\n\n");
    printf("\nGo to the Owner Login Page ->> ");
    OwnerLogin();
    printf("\033[1;32m");
    printf("\n\aYOU HAVE LOGGED IN TO THE DELIVERY SYSTEM SUCCESSFULLY\n");
    printf("\033[1;31m");
    printf("\n\n\aPlease Insert Your Menu Items -->> \n");
    TakingInput();
    printf("\033[1;32m");
    printf("\n\aYou Have Successfully Inserted Your Menu Items \n\n");
    printf("\033[1;31m");
    printf("\n\aGo to the Customer Login Page ->> ");
    CustomerLogin();
    printf("\033[1;32m");
    printf("\n\aYOU HAVE LOGGED IN TO THE DELIVERY SYSTEM SUCCESSFULLY");

    Choice();
    return 0;
}

// Function for owner login
void OwnerLogin() {
    printf("\033[0;32m");
    char name2[100], email2[25], phone2[15], password2[25];
    printf("\nEnter your name : ");
    fflush(stdin);
    gets(name2);
    printf("\nEnter your phone number : ");
    fflush(stdin);
    gets(phone2);
    printf("\nEnter your email : ");
    fflush(stdin);
    gets(email2);
    printf("\nEnter your password : ");
    fflush(stdin);
    gets(password2);
}

// Function for customer login
void CustomerLogin() {
    printf("\033[0;34m");
    char name[100], address[500], email[25], phone[15], password[25];
    printf("\nEnter your name : ");
    fflush(stdin);
    gets(name);
    printf("\nEnter your full address : ");
    fflush(stdin);
    gets(address);
    printf("\nEnter your phone number : ");
    fflush(stdin);
    gets(phone);
    printf("\nEnter your email : ");
    fflush(stdin);
    gets(email);
    printf("\nEnter your password : ");
    fflush(stdin);
    gets(password);
    strcpy(name1, name);
    strcpy(address1, address);
    strcpy(email1, email);
    strcpy(phone1, phone);
}

// Function for taking input of menu items
void TakingInput() {
    printf("\033[0;35m");
    char name[50];
    int price;
    printf("\nEnter the item name : ");
    fflush(stdin);
    gets(name);
    printf("\nEnter the price : ");
    scanf("%d", &price);
    Create(name, price);
}

// Function for creating menu items
void Create(char name[50], int price) {
    struct MenuItem *newnode;
    newnode = (struct MenuItem *)malloc(sizeof(struct MenuItem));
    newnode->serial = serial;
    strcpy(newnode->name, name);
    newnode->price = price;
    newnode->next = NULL;
    serial++;
    if (head == NULL) {
        head = temp = newnode;
    } else {
        temp->next = newnode;
        temp = newnode;
    }
    int choice;
    printf("\nWill You Wanna Add More Item (1 for yes 0 for no ) : ");
    scanf("%d", &choice);
    if (choice == 1) {
        TakingInput();
    } else if (choice == 0) {
        return;
    }
}

// Function for displaying menu items
void Menu() {
    printf("\033[0;32m");
    struct MenuItem *temp1;
    temp1 = head;
    if (temp1 == NULL)
        printf("\nMenu Is Empty !!!!!");
    while (temp1 != NULL) {
        printf("\n%d\t\t%s\t\t\t%d", temp1->serial, temp1->name, temp1->price);
        temp1 = temp1->next;
    }
}

// Function for ordering items
void Order() {
    printf("\033[0;34m");
    struct MenuItem *temp2;
    int Item, NumItem, Bill, ch;
    printf("\033[1;31m");
    printf("\nSelect the item : ");
    scanf("%d", &Item);
    printf("\033[1;32m");
    temp2 = head;
    while (temp2->serial != Item) {
        temp2 = temp2->next;
    }
    printf("\n How many %s do you want : ", temp2->name);
    scanf("%d", &NumItem);
    Bill = temp2->price * NumItem;
    printf("\nYour Bill For %d no of %s is : %d ", NumItem, temp2->name, Bill);
    total = total + Bill;
    printf("\nDo You Wanna Order More(1 for yes 0 for no) : ");
    scanf("%d", &ch);
    if (ch == 1) {
        Order();
    } else if (ch == 0) {
        return;
    }
}

// Function for selecting the way of ordering
void Choice() {
    printf("\033[0;36m");
    int n;
    printf("\n\nWe provide two types of orders ---> \n1.By Hotel\n2.By Food\n3.Exit\n");
    printf("\nENTER YOUR CHOICE--> ");
    scanf("%d", &n);
    switch (n) {
        case 1:
            ByHotel();
            break;
        case 2:
            ByFood();
            break;
        case 3:
            exit(1);
            break;
        default:
            printf("\nPlease press the right key ");
            main();
            break;
    }
}

// Function for ordering by hotel
void ByHotel() {
    printf("\033[0;32m");
    int n, choice;
    printf("\n1.Hotel Shreyan\n2.Tasty\n3.Tris Planet\n4.Lets Eat\n5.Dhakeshwari Resturant\n6.Go to the choice \n7.Exit");
    printf("\nSelect the hotel name --> ");
    scanf("%d", &choice);
    switch (choice) {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            printf("\n<---------THIS IS OUR MENU--------->\n\n");
            Menu();
            Order();
            break;
                case 6:
            Choice();
            break;
        case 7:
            printf("\nTHANK YOU FOR VISITING US \n");
            exit(1);
            break;
        default:
            printf("\nPLEASE ENTER THE CORRECT CHOICE\n");
            ByHotel();
            break;
    }
    printf("\033[1;31m");
    printf("\nGO TO THE PAYMENT GATEWAY(press 1 ) ->> ");
    scanf("%d", &n);
    if (n == 1)
        Payment();
}

// Function for ordering by food
void ByFood() {
    printf("\033[0;32m");
    int n;
    printf("\n<-----------THIS IS OUR MENU----------->\n\n");
    Menu();
    Order();
    printf("\033[1;31m");
    printf("\n\nGO TO THE PAYMENT GATEWAY(press 1 ) ->> ");
    scanf("%d", &n);
    if (n == 1)
        Payment();
}

// Function for payment
void Payment() {
    printf("\033[0;37m");
    int n, a;
    printf("\n1.ONLINE PAYMENT\n2.CASH ON DELIVERY\n");
    printf("ENTER YOUR CHOICE--> ");
    scanf("%d", &n);
    switch (n) {
        case 1:
            printf("\n1.PAYTM\n2.PHONEPAY\n3.GPAY\n4.CARD PAY\n");
            printf("ENTER YOUR CHOICE--> ");
            scanf("%d", &a);
            switch (a) {
                case 1:
                case 2:
                case 3:
                case 4:
                    printf("\nTHANK YOU !!!!!!! YOU WILL GET YOUR DELICIOUS FOOD JUST AFTER 30-40 MINUTES \n\n");
                    break;
                default:
                    printf("PLEASE SELECT THE CORRECT OPTION\n");
                    Payment();
                    break;
            }
            break;
        case 2:
            printf("\nTHANK YOU !!!!!!! YOU WILL GET YOUR DELICIOUS FOOD JUST AFTER 30-40 MINUTES \n\n");
            break;
        default:
            printf("PLEASE SELECT THE CORRECT OPTION\n");
            Payment();
            break;
    }
    int c;
    printf("\033[1;31m");
    printf("WOULD YOU WANT TO GENERATE YOUR INVOICE ?(press 1 for yes 0 for no)--> ");
    scanf("%d", &c);
    if (c == 1) {
        Invoice();
    } else {
        printf("\n THANK YOU!!! PLEASE COME AGAIN...");
    }
}

// Function for generating invoice
void Invoice() {
    printf("\033[0;35m");
    int total2;
    printf("\n\n\t|---------------------------------------------------------------------|\n");
    printf("\t\t             YOUR INVOICE                   \n");
    printf("\t|---------------------------------------------------------------------|\n\n");
    printf("\nName: %s\n", name1);
    printf("Address: %s\n", address1);
    printf("Phone: %s\n", phone1);
    printf("Email: %s\n", email1);
    int gst;
    gst = total + total * .18;
    total2 = gst;
    printf("\nYOUR TOTAL BILL IS--> %d (including 18 percent GST)\n", total2);
    printf("\n THANK YOU!!! PLEASE COME AGAIN...");
}


