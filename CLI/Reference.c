#include <stdio.h>
#include <conio.h>

int main() {
    char password[20];
    int i = 0;
    
    printf("Enter password: ");
    while (1) {
        char ch = getch();
        if (ch == '\r') { // Enter key
            password[i] = '\0'; // Null-terminate the string
            break;
        } else if (ch == '\b' && i > 0) { // Backspace key
            printf("\b \b"); // Move cursor back, overwrite character, move cursor back again
            i--;
        } else if (ch != '\b') { // Ignore backspace character
            password[i++] = ch;
            printf("*"); // Print '*' instead of the actual character
        }
    }

    printf("\nEntered password: %s\n", password);
    return 0;
}
