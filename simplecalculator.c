#include<stdio.h>
#include<stdlib.h>
int main() 
{
	int num1, num2;
	char choice;
	float res;
	printf("enter your choice \n");
	scanf("%c", &choice);
	printf("enter 1st number \n");
	scanf("%d", &num1);
	printf("enter 2nd number \n");
	scanf("%d", &num2);
	switch (choice)
	{
		case'+' : res = num1 + num2;
			break;
		case'-' : res = num1 - num2;
			break;
		case'*' : res = num1 * num2;
			break;
		case'/' : res = num1 / num2;
			break;
		case'%' : res = num1 % num2;
			break;
		default : printf("invalid operation \n");
	}
	printf("result: %d %c %d = %f \n", num1, choice, num2, res);
	return 0;
}