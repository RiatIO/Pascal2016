# Code file created by Pascal2016 compiler 2016-11-09 16:34:39
        .global main                         
main:
        call    prog$primes_1           # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$primes_1:
        enter   $4032,$1                # start of primes
proc$findprimes_2:
        enter   $40,$2                  
.L0003:
                                        # Start while-statement
        cmpl    $0,%eax                 
        je      .L0004                  
.L0005:
                                        # Start while-statement
        cmpl    $0,%eax                 
        je      .L0006                  
        jmp     .L0005                  
.L0006:
                                        # End while-statement
        jmp     .L0003                  
.L0004:
                                        # End while-statement
                leave                   
                ret                     
proc$p4_7:
        enter   $32,$3                  
        movl    $1000,%eax              
        pushl   %eax                    
        call    write_int               
        addl    $4,%esp                 
        cmpl    $0,%eax                 
        je      .L0008                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
.L0008:
        movl    $100,%eax               
        pushl   %eax                    
        call    write_int               
        addl    $4,%esp                 
        cmpl    $0,%eax                 
        je      .L0009                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
.L0009:
        movl    $10,%eax                
        pushl   %eax                    
        call    write_int               
        addl    $4,%esp                 
        cmpl    $0,%eax                 
        je      .L0010                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
.L0010:
                leave                   
                ret                     
proc$printprimes_11:
        enter   $40,$4                  
.L0012:
                                        # Start while-statement
        cmpl    $0,%eax                 
        je      .L0013                  
        cmpl    $0,%eax                 
        je      .L0014                  
        cmpl    $0,%eax                 
        je      .L0015                  
.L0015:
.L0014:
        jmp     .L0012                  
.L0013:
                                        # End while-statement
                leave                   
                ret                     
.L0016:
                                        # Start while-statement
        cmpl    $0,%eax                 
        je      .L0017                  
        jmp     .L0016                  
.L0017:
                                        # End while-statement
        leave                           
        ret                             
