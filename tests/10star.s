# Code file created by Pascal2016 compiler 2016-11-09 16:34:37
        .global main                         
main:
        call    prog$tenstars_1         # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$tenstars_1:
        enter   $36,$1                  # start of tenstars
.L0002:
                                        # Start while-statement
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    $42,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        jmp     .L0002                  
.L0003:
                                        # End while-statement
        leave                           
        ret                             
