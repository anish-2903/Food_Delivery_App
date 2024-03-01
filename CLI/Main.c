#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function prototypes
void customerLogin();
void ownerLogin();




// Global Variables
int serial = 1;
int total = 0;
char customerName[50];
char customerAddress[50];
char customerEmail[50];
char customerPhone[50];


// Structure for menu
struct menuItem {
    int serial;
    float price;
    char name[50];
    struct menuItem *next;
};


// Global pointers
struct menuItem *head = NULL;
struct menuItem *temp = NULL;


int main() {

    void ownerLogin();


    return 0;
}

// Customer Login Function
void customerLogin() {

}

// Owner Login Function
void ownerLogin() {
    printf("\033[0;32m"); // This changes the text color to green

    char ownerName[50];
    char ownerEmail[30];
    char ownerPhone[15];
    char ownerPassword[25];

    
    printf("\nEnter your name: ");
    fgets(ownerName, sizeof(ownerName), stdin);
    ownerName[strcspn(ownerName, "\n")] = '\0'; // Remove newline character

    printf("\nEnter your phone number: ");
    fgets(ownerPhone, sizeof(ownerPhone), stdin);
    ownerPhone[strcspn(ownerPhone, "\n")] = '\0'; // Remove newline character

    printf("\nEnter your email: ");
    fgets(ownerEmail, sizeof(ownerEmail), stdin);
    ownerEmail[strcspn(ownerEmail, "\n")] = '\0'; // Remove newline character

    printf("\nEnter your password: ");
    fgets(ownerPassword, sizeof(ownerPassword), stdin);
    ownerPassword[strcspn(ownerPassword, "\n")] = '\0'; // Remove newline character

}
