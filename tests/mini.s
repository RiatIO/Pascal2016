# Code file created by Pascal2016 compiler 2016-11-10 11:42:58
        .globl main                         
main:
        call    prog$mini_1             # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$mini_1:
        enter   $32,$1                  # start of mini
        movl    $120,%eax               #     'x'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        leave                           # End of mini
        ret                             