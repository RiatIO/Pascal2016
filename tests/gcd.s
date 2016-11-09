# Code file created by Pascal2016 compiler 2016-11-09 16:34:38
        .global main                         
main:
        call    prog$gcd_1              # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$gcd_1:
        enter   $36,$1                  # start of gcd
func$gcd_2:
        enter   $32,$2                  
        movl    $0,%eax                 
        pushl   %eax                    
        call    write_int               
        addl    $4,%esp                 
        cmpl    $0,%eax                 
        je      .L0003                  
        jmp     .L0004                  
.L0003:
.L0004:
        movl    -32(%ebp),%eax          
                leave                   
                ret                     
        movl    $71,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $67,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $68,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $40,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $44,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $41,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        leave                           
        ret                             
