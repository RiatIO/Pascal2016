# Code file created by Pascal2016 compiler 2016-11-10 15:31:15
        .globl main                         
main:
        call    prog$gcd_1              # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
func$gcd_2:
        enter   $32,$2                  # Start of gcd
                                        # Start if-statement
        movl    -4(%ebp),%edx           
        movl    -32(%edx),%eax          #  n
        pushl   %eax                    
        movl    $0,%eax                 # 0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        sete    %al                     # Test =
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    -4(%ebp),%edx           
        movl    -32(%edx),%eax          #  m
        movl    -8(%ebp),%edx           
        movl    %eax,-32(%edx)          #  gcd :=
        jmp     .L0004                  
.L0003:
        movl    -4(%ebp),%edx           
        movl    -32(%edx),%eax          #  m
        movl    -4(%ebp),%edx           
        movl    -32(%edx),%eax          #  n
        pushl   %eax                    # Push param #2
        movl    -4(%ebp),%edx           
        movl    -32(%edx),%eax          #  n
        pushl   %eax                    # Push param #1
        call    func$gcd_5              
        addl    $8,%esp                 
        movl    -8(%ebp),%edx           
        movl    %eax,-32(%edx)          #  gcd :=
.L0004:
                                        # End if-statement
        movl    -32(%ebp),%eax          
                leave                   
                ret                     
prog$gcd_1:
        enter   $36,$1                  # start of gcd
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #     res
        movl    $462,%eax               #     462
        pushl   %eax                    # Push param #2
        movl    $1071,%eax              #     1071
        pushl   %eax                    # Push param #1
        call    func$gcd_6              
        addl    $8,%esp                 
        movl    -100(%ebp),%edx         
        movl    %eax,-32(%edx)          #  res :=
        movl    $71,%eax                #     'G'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $67,%eax                #     'C'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $68,%eax                #     'D'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $40,%eax                #     '('
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $1071,%eax              #     1071
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $44,%eax                #     ','
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $462,%eax               #     462
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $41,%eax                #     ')'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $61,%eax                #     '='
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #     res
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $10,%eax                #     10
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        leave                           # End of gcd
        ret                             
