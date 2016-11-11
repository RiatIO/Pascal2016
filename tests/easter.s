# Code file created by Pascal2016 compiler 2016-11-11 11:58:42
        .globl main                         
main:
        call    prog$easter_1           # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$easter_2:
        enter   $92,$2                  # Start of easter
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   y
        pushl   %eax                    
        movl    $19,%eax                #   19
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # a :=
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   y
        pushl   %eax                    
        movl    $100,%eax               #   100
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-40(%edx)          # b :=
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   y
        pushl   %eax                    
        movl    $100,%eax               #   100
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        movl    -8(%ebp),%edx           
        movl    %eax,-44(%edx)          # c :=
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   b
        pushl   %eax                    
        movl    $4,%eax                 #   4
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-48(%edx)          # d :=
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   b
        pushl   %eax                    
        movl    $4,%eax                 #   4
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        movl    -8(%ebp),%edx           
        movl    %eax,-52(%edx)          # e :=
        pushl   %eax                    
        movl    $25,%eax                #   25
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-56(%edx)          # f :=
        pushl   %eax                    
        movl    $3,%eax                 #   3
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-60(%edx)          # g :=
        pushl   %eax                    
        movl    $30,%eax                #   30
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        movl    -8(%ebp),%edx           
        movl    %eax,-64(%edx)          # h :=
        movl    -8(%ebp),%edx           
        movl    -44(%edx),%eax          #   c
        pushl   %eax                    
        movl    $4,%eax                 #   4
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-68(%edx)          # i :=
        movl    -8(%ebp),%edx           
        movl    -44(%edx),%eax          #   c
        pushl   %eax                    
        movl    $4,%eax                 #   4
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        movl    -8(%ebp),%edx           
        movl    %eax,-72(%edx)          # k :=
        pushl   %eax                    
        movl    $7,%eax                 #   7
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        movl    -8(%ebp),%edx           
        movl    %eax,-76(%edx)          # l :=
        pushl   %eax                    
        movl    $451,%eax               #   451
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-80(%edx)          # m :=
        pushl   %eax                    
        movl    $31,%eax                #   31
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        movl    -8(%ebp),%edx           
        movl    %eax,-84(%edx)          # month :=
        pushl   %eax                    
        movl    $31,%eax                #   31
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -8(%ebp),%edx           
        movl    %eax,-88(%edx)          # day :=
                                        # Start if-statement
        movl    -8(%ebp),%edx           
        movl    -84(%edx),%eax          #   month
        pushl   %eax                    
        movl    $3,%eax                 #   3
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        sete    %al                     # Test =
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    -8(%ebp),%edx           
        movl    -88(%edx),%eax          #   day
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $32,%eax                #   ' '
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $77,%eax                #   'M'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $97,%eax                #   'a'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $114,%eax               #   'r'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $99,%eax                #   'c'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $104,%eax               #   'h'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $32,%eax                #   ' '
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   y
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $10,%eax                #   10
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        jmp     .L0004                  
.L0003:
        movl    -8(%ebp),%edx           
        movl    -88(%edx),%eax          #   day
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $32,%eax                #   ' '
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $65,%eax                #   'A'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $112,%eax               #   'p'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $114,%eax               #   'r'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $105,%eax               #   'i'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $108,%eax               #   'l'
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    $32,%eax                #   ' '
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   y
        pushl   %eax                    # Push next param.
        call    write_int               
        addl    $4,%esp                 # Pop param.
        movl    $10,%eax                #   10
        pushl   %eax                    # Push next param.
        call    write_char              
        addl    $4,%esp                 # Pop param.
.L0004:
                                        # End if-statement
        leave                           # End of easter
        ret                             
prog$easter_1:
        enter   $36,$1                  # Start of easter
        movl    $2010,%eax              #   2010
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # y :=
.L0005:
                                        # Start while-statement
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   y
        pushl   %eax                    
        movl    $2020,%eax              #   2020
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setle   %al                     # Test <=
        cmpl    $0,%eax                 
        je      .L0006                  
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   y
        pushl   %eax                    # Push next param.
        call    proc$easter_2           
        addl    $8,%esp                 
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   y
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # y :=
        jmp     .L0005                  
.L0006:
                                        # End while-statement
        leave                           # End of easter
        ret                             
