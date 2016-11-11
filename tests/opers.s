# Code file created by Pascal2016 compiler 2016-11-11 10:04:56
        .globl main                         
main:
        call    prog$operatortest_1     # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$testunaryboolean_2:
        enter   $32,$2                  
        movl    $0,%eax                 #     0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $1,%eax                 #     1
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
                leave                   
                ret                     
proc$testbinaryboolean_3:
        enter   $32,$4                  
        movl    $0,%eax                 #     0
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #     0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 #     0
        pushl   %eax                    # Push next param.
        movl    $1,%eax                 #     1
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $1,%eax                 #     1
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 #     0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $1,%eax                 #     1
        pushl   %eax                    # Push next param.
        movl    $1,%eax                 #     1
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
                leave                   
                ret                     
proc$testunarynumeric_4:
        enter   $32,$6                  
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $11,%eax                # 11
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
                leave                   
                ret                     
proc$testbinarynumeric_5:
        enter   $32,$8                  
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        movl    $11,%eax                # 11
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $11,%eax                # 11
        pushl   %eax                    # Push next param.
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $11,%eax                # 11
        pushl   %eax                    # Push next param.
        movl    $11,%eax                # 11
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        movl    $17,%eax                # 17
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        movl    $11,%eax                # 11
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        movl    $0,%eax                 # 0
        pushl   %eax                    # Push next param.
        call    proc$test               
        addl    $8,%esp                 
                leave                   
                ret                     
prog$operatortest_1:
        enter   $32,$1                  # Start of operatortest
        call    proc$testunaryboolean   
        addl    $8,%esp                 
        call    proc$testunarynumeric   
        addl    $8,%esp                 
        call    proc$testbinaryboolean  
        addl    $8,%esp                 
        call    proc$testbinarynumeric  
        addl    $8,%esp                 
        leave                           # End of operatortest
        ret                             
