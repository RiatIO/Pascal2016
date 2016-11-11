# Code file created by Pascal2016 compiler 2016-11-11 18:26:37
        .globl main                         
main:
        call    prog$gcd_1              # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
func$gcd_2:
        enter   $32,$2                  # Start of gcd
                                        # Start if-statement
        movl    -8(%ebp),%edx           
        movl    12(%edx),%eax           #   n
        pushl   %eax                    
        movl    $0,%eax                 #   0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        sete    %al                     # Test =
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   m
        movl    -8(%ebp),%edx           
        movl    %eax,-32(%edx)          # gcd :=
        jmp     .L0004                  
.L0003:
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   m
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    12(%edx),%eax           #   n
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        pushl   %eax                    # Push param #2
        movl    -8(%ebp),%edx           
        movl    12(%edx),%eax           #   n
        pushl   %eax                    # Push param #1
        call    func$gcd_2              
        addl    $8,%esp                 # Pop parameters
        movl    -8(%ebp),%edx           
        movl    %eax,-32(%edx)          # gcd :=
.L0004:
                                        # End if-statement
        movl    -32(%ebp),%eax          # Fetch return value
        leave                           # End of gcd
        ret                             
prog$gcd_1:
        enter   $36,$1                  # Start of gcd
        movl    $462,%eax               #   462
        pushl   %eax                    # Push param #2
        movl    $1071,%eax              #   1071
        pushl   %eax                    # Push param #1
        call    func$gcd_2              
        addl    $8,%esp                 # Pop parameters
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # res :=
        movl    $10,%eax                #   10
        pushl   %eax                    # Push param #11.
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   res
        pushl   %eax                    # Push param #10.
        movl    $61,%eax                #   '='
        pushl   %eax                    # Push param #9.
        movl    $41,%eax                #   ')'
        pushl   %eax                    # Push param #8.
        movl    $462,%eax               #   462
        pushl   %eax                    # Push param #7.
        movl    $44,%eax                #   ','
        pushl   %eax                    # Push param #6.
        movl    $1071,%eax              #   1071
        pushl   %eax                    # Push param #5.
        movl    $40,%eax                #   '('
        pushl   %eax                    # Push param #4.
        movl    $68,%eax                #   'D'
        pushl   %eax                    # Push param #3.
        movl    $67,%eax                #   'C'
        pushl   %eax                    # Push param #2.
        movl    $71,%eax                #   'G'
        pushl   %eax                    # Push param #1.
        leave                           # End of gcd
        ret                             
