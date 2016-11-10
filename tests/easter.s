# Code file created by Pascal2016 compiler 2016-11-09 16:45:32
        .global main                         
main:
        call    prog$easter_1           # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$easter_2:
        enter   $92,$2                  
        movl    $3,%eax                 
        pushl   %eax                    
        call    write_int               
        addl    $4,%esp                 
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $77,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $97,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $114,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $99,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $104,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        jmp     .L0004                  
.L0003:
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $65,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $112,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $114,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $105,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $108,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
.L0004:
                leave                   
                ret                     
.L0005:
                                        # Start while-statement
        movl    $2020,%eax              
        pushl   %eax                    
        call    write_int               
        addl    $4,%esp                 
        cmpl    $0,%eax                 
        je      .L0006                  
        jmp     .L0005                  
.L0006:
                                        # End while-statement
prog$easter_1:
        enter   $36,$1                  # start of easter
        leave                           
        ret                             
