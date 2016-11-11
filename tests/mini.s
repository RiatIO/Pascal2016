# Code file created by Pascal2016 compiler 2016-11-11 18:26:38
        .globl main                         
main:
        call    prog$mini_1             # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$mini_1:
        enter   $32,$1                  # Start of mini
        movl    $120,%eax               #   'x'
        pushl   %eax                    # Push param #1.
        leave                           # End of mini
        ret                             
