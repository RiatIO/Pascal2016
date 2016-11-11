# Code file created by Pascal2016 compiler 2016-11-11 11:58:43
        .globl main                         
main:
        call    prog$operatortest_1     # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$testunaryboolean_2:
        enter   $32,$2                  # Start of testunaryboolean
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $1,%eax                 #   1
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        leave                           # End of testunaryboolean
        ret                             
proc$testbinaryboolean_3:
        enter   $32,$4                  # Start of testbinaryboolean
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        movl    $1,%eax                 #   1
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $1,%eax                 #   1
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $1,%eax                 #   1
        pushl   %eax                    # Push next param.
        movl    $1,%eax                 #   1
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        leave                           # End of testbinaryboolean
        ret                             
proc$testunarynumeric_4:
        enter   $32,$6                  # Start of testunarynumeric
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $11,%eax                #   11
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        leave                           # End of testunarynumeric
        ret                             
proc$testbinarynumeric_5:
        enter   $32,$8                  # Start of testbinarynumeric
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        movl    $11,%eax                #   11
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $11,%eax                #   11
        pushl   %eax                    # Push next param.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $11,%eax                #   11
        pushl   %eax                    # Push next param.
        movl    $11,%eax                #   11
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        movl    $11,%eax                #   11
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push next param.
        call    proc$null               
        addl    $8,%esp                 
        leave                           # End of testbinarynumeric
        ret                             
prog$operatortest_1:
        enter   $32,$1                  # Start of operatortest
        call    proc$testunaryboolean_2 
        addl    $8,%esp                 
        call    proc$testunarynumeric_4 
        addl    $8,%esp                 
        call    proc$testbinaryboolean_3 
        addl    $8,%esp                 
        call    proc$testbinarynumeric_5 
        addl    $8,%esp                 
        leave                           # End of operatortest
        ret                             
