scanner = { 
  source = ""  
}

scanner.__index= scanner

function scanner.new(s)
  scanner.source = s
end

function scanner:__tostring()
  return string.format("< %s >", self.source)
end

--return scanner

scanner.new("Hello")

print("tbl is " .. scanner)
print(scanner.source)


