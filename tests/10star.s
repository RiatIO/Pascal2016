# Code file created by Pascal2016 compiler 2016-11-11 18:26:37
        .globl main                         
main:
        call    prog$tenstars_1         # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$tenstars_1:
        enter   $36,$1                  # Start of tenstars
        movl    $0,%eax                 #   0
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
.L0002:
                                        # Start while-statement
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    
        movl    $10,%eax                #   10
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setl    %al                     # Test <
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    $42,%eax                #   '*'
        pushl   %eax                    # Push param #1.
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
        jmp     .L0002                  
.L0003:
                                        # End while-statement
        movl    $10,%eax                #   10
        pushl   %eax                    # Push param #1.
        leave                           # End of tenstars
        ret                             
