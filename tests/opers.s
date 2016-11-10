# Code file created by Pascal2016 compiler 2016-11-09 18:12:33
        .globl main                         
main:
        call    prog$operatortest_1     # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$testunaryboolean_2:
        enter   $32,$2                  
proc$test_3:
        enter   $32,$3                  
                leave                   
                ret                     
                leave                   
                ret                     
proc$testbinaryboolean_4:
        enter   $32,$4                  
proc$test_5:
        enter   $32,$5                  
                leave                   
                ret                     
                leave                   
                ret                     
proc$testunarynumeric_6:
        enter   $32,$6                  
proc$test_7:
        enter   $32,$7                  
                leave                   
                ret                     
                leave                   
                ret                     
proc$testbinarynumeric_8:
        enter   $32,$8                  
proc$test_9:
        enter   $32,$9                  
                                        # Start if-statement
        cmpl    $0,%eax                 
        je      .L0010                  
.L0010:
                                        # End if-statement
                leave                   
                ret                     
                leave                   
                ret                     
prog$operatortest_1:
        enter   $32,$1                  # start of operatortest
        leave                           
        ret                             
