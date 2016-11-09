# Code file created by Pascal2016 compiler 2016-11-09 16:34:38
        .global main                         
main:
        call    prog$mini_1             # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$mini_1:
        enter   $32,$1                  # start of mini
        movl    $120,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        leave                           
        ret                             
