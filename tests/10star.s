# Code file created by Pascal2016 compiler 2016-11-10 17:24:32
        .globl main                         
main:
        call    prog$tenstars_1         # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$tenstars_1:
        enter   $36,$1                  # start of tenstars
        movl    $0,%eax                 # 0
        movl    -100(%ebp),%edx         
        movl    %eax,-32(%edx)          #  i :=
.L0002:
                                        # Start while-statement
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #     i
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    $42,%eax                #     '*'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #     i
        movl    $1,%eax                 # 1
        movl    -100(%ebp),%edx         
        movl    %eax,-32(%edx)          #  i :=
        jmp     .L0002                  
.L0003:
                                        # End while-statement
        movl    $10,%eax                #     10
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        leave                           # End of tenstars
        ret                             
