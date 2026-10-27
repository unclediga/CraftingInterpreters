hadError = false

local function run(line)
	print("running ... :" .. line)
end

--------------------------------------------------------------------

local function runPrompt()
	repeat
		io.write(">")
		line = io.read("*line")
		if line == "" then
			break
		end
		run(line)
		hadError = false
	until false
	print("Goodbye!")
end

--------------------------------------------------------------------

local function runFile(fname)
	f = io.open(fname, "r")
	if f == nil then
		print("Can't open file " .. fname)
		return
	end
	code = f:read("*a")
	run(code)
	f:close()
end

--------------------------------------------------------------------

local function report(line, where, message)
	print("[line " .. line .. "] Error" .. where .. ": " .. message)
	hadError = true
end

--------------------------------------------------------------------

function main()
	if #arg > 0 then
		runFile(arg[1])
	else
		runPrompt()
	end
end

--------------------------------------------------------------------

function error_(line, message)
	report(line, "", message)
end

--------------------------------------------------------------------

main()
