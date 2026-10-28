scanner1 = { }
--scanner1.source = "AAA"
scanner1.source2 = ""  
scanner1.new = function (s)
  scanner1.source = s
end

function scanner1:__tostring()
  print(self)
  return string.format("< %s >", "")
end

--return scanner
s1 = {['source']= "aa1"}
s2 = {['source']= "aa2"}

setmetatable(s1,{__index=scanner1})
setmetatable(s2,{__index=scanner1})
-- s2.__index = scanner1  NOT WORK

s1.new("Hello One")
s2.new("Hello Two")
print("s1 = " .. s1.source)
print("s2 = " .. s2.source)

print("tbl is " .. s1.__tostring())
print("tbl is " .. s2.__tostring())



